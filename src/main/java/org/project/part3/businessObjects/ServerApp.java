package org.project.part3.businessObjects;

import org.project.part3.DAOs.MovieDaoInterface;
import org.project.part3.DAOs.MySqlMovieDao;
import org.project.part3.DTOs.Movie;
import org.project.part3.exceptions.DaoException;
import org.project.part3.json.MovieJsonSerializer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.javatuples.Pair;
import java.util.List;

public class ServerApp {
   public static void main(String[] args) {
      ServerApp server = new ServerApp();
      server.start();
   }

   public void start() {
      MovieDaoInterface dao = new MySqlMovieDao();
      Gson gson = new GsonBuilder().registerTypeAdapter(Pair.class, new MovieJsonSerializer()).create();

      try {
         ServerSocket ss = new ServerSocket(8080);
         System.out.println("Server: Server started. Listening for connections on port 8080...");

         int clientNumber = 0;

         while (true) {
            Socket socket = ss.accept();
            clientNumber++;

            System.out.println("Server: Client " + clientNumber + " has connected.");
            System.out.println("Server: Port# of remote client: " + socket.getPort());
            System.out.println("Server: Port# of this server: " + socket.getLocalPort());

            Thread t = new Thread(new ClientHandler(socket, clientNumber, dao, gson));
            t.start();

            System.out.println("Server: ClientHandler started in thread for client " + clientNumber + ". ");
            System.out.println("Server: Listening for further connections...");
         }
      } catch (IOException e) {
         System.out.println("Server: IOException: " + e);
      }
      System.out.println("Server: Server exiting, Goodbye!");
   }

   public static class ClientHandler implements Runnable {
      BufferedReader socketReader;
      PrintWriter socketWriter;
      Socket socket;
      int clientNumber;
      MovieDaoInterface dao;
      Gson gson;

      public ClientHandler(Socket clientSocket, int clientNumber, MovieDaoInterface dao, Gson gson) {
         this.dao = dao;
         this.clientNumber = clientNumber;
         this.socket = clientSocket;
         this.gson = gson;

         try {
            InputStreamReader isReader = new InputStreamReader(clientSocket.getInputStream());
            OutputStream os = clientSocket.getOutputStream();
            this.socketReader = new BufferedReader(isReader);
            this.socketWriter = new PrintWriter(os, true);
         } catch (IOException ex) {
            System.out.println("Server: IOException: " + ex);
            ex.printStackTrace();
         }
      }

      @Override
      public void run() {
         String message;
         String[] tokens;

         try {
            while ((message = socketReader.readLine()) != null) {
               tokens = message.split(";");
               System.out.println("Server: (ClientHandler): Read command from client " + clientNumber + ": " + message);

               switch (message.split(";")[0]) {
                  case "FIND_MOVIE_BY_ID":
                     try {
                        int id = Integer.parseInt(tokens[1]);

                        Pair<Integer, Movie> movie = dao.findMovieById(id);

                        if (movie.getValue1() == null)
                           socketWriter.println("Error: Movie with id " + id + " not found.");
                        else
                           socketWriter.println(gson.toJson(movie));
                     } catch (NumberFormatException e) {
                        socketWriter.println("Error: 'id' must be a number.");
                     } catch (DaoException e) {
                        socketWriter.println("Error: Database operation failed.");
                     }
                     break;
                  case "DISPLAY_ALL_MOVIES":
                     try {
                        List<Pair<Integer, Movie>> movies = dao.findAllMovies();

                        if (movies == null)
                           socketWriter.println("Error: No movies found.");
                        else
                           socketWriter.println(gson.toJson(movies));
                     } catch (DaoException e) {
                        socketWriter.println("Error: Database operation failed.");
                     }
                     break;
                  case "ADD_MOVIE":
                     try {
                        Movie movie = gson.fromJson(tokens[1], Movie.class);
                        dao.addMovie(movie);
                        socketWriter.println("Success: Movie " + movie.getTitle() + " added.");
                     } catch (DaoException e) {
                        socketWriter.println("Error: Database operation failed.");
                     }
                     break;
                  case "DELETE_MOVIE":
                     try {
                        int id = Integer.parseInt(tokens[1]);

                        Pair<Integer, Movie> movie = dao.findMovieById(id);

                        if (movie.getValue1() == null)
                           socketWriter.println("Error: Movie with id " + id + " not found.");
                        else {
                           dao.deleteMovieById(id);
                           socketWriter.println("Success: Movie \"" + movie.getValue1().getTitle() + "\" (id: " + id + ") has been deleted!");
                        }
                     } catch (NumberFormatException e) {
                        socketWriter.println("Error: 'id' must be a number.");
                     } catch (DaoException e) {
                        socketWriter.println("Error: Database operation failed.");
                     }
                     break;
                  default:
                     socketWriter.println("Error: Unknown command.");
                     break;
               }
               socketWriter.flush();
            }

            socket.close();
            System.out.println("Server: (ClientHandler): Handler for Client " + clientNumber + " is terminating .....");
         } catch (IOException ioException) {
            ioException.printStackTrace();
         }
      }

   }
}
