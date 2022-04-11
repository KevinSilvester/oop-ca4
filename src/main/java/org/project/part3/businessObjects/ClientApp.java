package org.project.part3.businessObjects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.javatuples.Pair;
import org.project.part3.json.MovieJsonDeserializer;
import org.project.part3.DTOs.Movie;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ClientApp {
   private static final Scanner KB = new Scanner(System.in);
   private static final Pattern PATTERN = Pattern.compile("^[0-9]{1,}$");
   private static final Gson GSON = new GsonBuilder().registerTypeAdapter(Pair.class, new MovieJsonDeserializer()).create();

   public static void main(String[] args) {
       ClientApp client = new ClientApp();
       client.start();
   }

   public void start() {
      final int FIND_MOVIE_BY_ID    = 1;
      final int DISPLAY_ALL_MOVIES  = 2;
      final int ADD_MOVIE           = 3;
      final int DELETE_MOVIE_BY_ID  = 4;
      final int EXIT                = 5;
      boolean exit = false;

      try {
         Socket socket = new Socket("localhost", 8080);
         OutputStream os = socket.getOutputStream();
         PrintWriter socketWriter = new PrintWriter(os, true);
         Scanner socketReader = new Scanner(socket.getInputStream());

         System.out.println("Client: Port# of client is: " + socket.getLocalPort());
         System.out.println("Client: Port# of server is: " + socket.getPort());
         System.out.println("Client message: The client is running and has connected to the server.");

         while (!exit) {
            switch (printMenu()) {
               case FIND_MOVIE_BY_ID:
                  System.out.print("\nClient message: Enter the movie ID: ");
                  String id = KB.nextLine();
                  String movieJson = null;

                  if (isValidID(id)) {
                     socketWriter.println("FIND_MOVIE_BY_ID;" + id);
                     movieJson = socketReader.nextLine();

                     if (movieJson.startsWith("Error"))
                        System.out.println("Client message: " + movieJson);
                     else {
                        Pair<Integer, Movie> movie = GSON.fromJson(movieJson, Pair.class);
                        printTableTitle("Movie Found");
                        printTableHeader(null, true);
                        movie.getValue1().display(movie.getValue0());
                     }
                  } else
                     System.out.println("\nClient message: Invalid ID");
                  break;
               case DISPLAY_ALL_MOVIES:
                  System.out.println("\nClient message: Displaying all movies...");

                  socketWriter.println("DISPLAY_ALL_MOVIES;");
                  String moviesJson = socketReader.nextLine();

                  if (moviesJson.startsWith("Error"))
                     System.out.println("Client message: " + moviesJson);
                  else {
                     printTableTitle("All Movies");
                     printTableHeader(null, true);
                     Pair<Integer, Movie>[] movies = GSON.fromJson(moviesJson, Pair[].class);
                     Arrays.stream(movies).forEach(pair -> pair.getValue1().display(pair.getValue0()));
                  }
                  break;
               case ADD_MOVIE:
                  System.out.println("\nClient message: Enter new movie details");
                  Movie movie = getMovieDetails();

                  if (movie != null) {
                     movieJson = GSON.toJson(movie, Movie.class);
                     System.out.println("Client message: Adding movie...");
                     socketWriter.println("ADD_MOVIE;" + movieJson);
                     String response = socketReader.nextLine();
                     System.out.println("Client message: " + response);
                  } else
                     System.out.println("\nClient message: Invalid movie details");
                  break;
               case DELETE_MOVIE_BY_ID:
                  System.out.print("\nClient message: Enter the movie ID: ");
                  id = KB.nextLine();

                  if (isValidID(id)) {
                     socketWriter.println("DELETE_MOVIE;" + id);
                     String response = socketReader.nextLine();
                     System.out.println("Client message: Deleting movie...");
                     System.out.println("Client message: " + response);
                  } else
                     System.out.println("\nClient message: Invalid ID");
                  break;
               case EXIT:
                  exit = true;
                  break;
               default:
                  System.out.println("\nInvalid option - please enter number in range");
                  break;
            }
         }

         socket.close();
         socketWriter.close();
         socketReader.close();
         os.close();
      }
      catch (NumberFormatException e) {
         System.out.println("\nInvalid option - please enter number in range");
      }
      catch (IOException e) {
         System.out.println("\nClient message: IOException: "+e);
      }
   }

   private int printMenu() throws NumberFormatException {
      final String MENU = "\n*** MENU ***\n"
            + "1. Find Movie By ID\n"
            + "2. Display all Movies\n"
            + "3. Add Movie\n"
            + "4. Delete Movie by ID\n"
            + "5. Exit\n"
            + "Enter Option [1, 5]: ";

      System.out.print("\n" + MENU);
      return Integer.parseInt(KB.nextLine());
   }

   private boolean isValidID(String id) {
      return PATTERN.matcher(id).find();
   }

   private void printTableTitle(String title) {
      System.out.println(
            "\n============================================\n" +
            "\t\t\t" + title + "\n" +
            "============================================");
   }

   private void printTableHeader(String optionalMessage, boolean... showId) {
      int idTab = -7;
      int titleTab = -40;
      int yearTab = -7;
      int boxOfficeTab = -12;
      int directorTab = -20;
      int actorsTab = -18;

      if (optionalMessage != null)
         System.out.println("\n" + optionalMessage);

      if (showId.length > 0 && showId[0])
         System.out.printf(
               "%" + idTab + "s" +
               "%" + titleTab + "s" +
               "%" + yearTab + "s" +
               "%" + boxOfficeTab + "s" +
               "%" + directorTab + "s" +
               "%" + actorsTab + "s%n",
               "ID", "Title", "Year", "BoxOffice", "Director", "Actors");
      else
         System.out.printf(
               "%" + titleTab + "s" +
               "%" + yearTab + "s" +
               "%" + boxOfficeTab + "s" +
               "%" + directorTab + "s" +
               "%" + actorsTab + "s%n",
               "Title", "Year", "BoxOffice", "Director", "Actors");
   }

   private Movie getMovieDetails() {
      try {
         System.out.print("\nEnter Movie Title: ");
         String title = KB.nextLine();

         System.out.print("Enter Movie Year: ");
         int year = Integer.parseInt(KB.nextLine());

         System.out.print("Enter Movie BoxOffice(in millions eg. €20.2): €");
         double boxOffice = Double.parseDouble(KB.nextLine());

         System.out.print("Enter Movie Director: ");
         String director = KB.nextLine();

         System.out.print("Enter Movie Lead Actor: ");
         String actors = KB.nextLine();

         return new Movie(title, year, boxOffice, director, actors);
      } catch (InputMismatchException | NumberFormatException e) {
         System.out.print("Invalid input - BoxOffice and Year must be a number");
      }
      return null;
   }
}
