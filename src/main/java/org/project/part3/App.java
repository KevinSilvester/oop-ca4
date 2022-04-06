package org.project.part3;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.javatuples.Pair;
import org.project.part3.comparators.*;
import org.project.part3.DAOs.MovieDaoInterface;
import org.project.part3.DAOs.MySqlMovieDao;
import org.project.part3.DTOs.Movie;
import org.project.part3.enumerators.SortType;
import org.project.part3.exceptions.DaoException;
import org.project.part3.jsonSerializers.MovieJsonSerializer;

import java.util.*;


public class App {
   private static final Scanner KB = new Scanner(System.in);
   private static final Gson GSON = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Pair.class, new MovieJsonSerializer()).create();
   private static List<Movie> movieList;
   private static Map<Integer, Movie> movieHashMap;
   private static Map<String, String> stringTreeMap;
   private static Queue<Movie> movieQueue;
   private static Queue<Movie> movieSecondQueue;
   private static MovieDaoInterface IMovieDao;

   public static void main(String[] args) {
      App app = new App();
      app.start();
   }

   private void start() {
      System.out.println("Hello World");
      System.out.println("Project part 2 - CA5");
      movieList = new ArrayList<>(10);
      movieHashMap = new HashMap<>();
      stringTreeMap = new TreeMap<>(new MapKeyComparator(SortType.Ascending));
      movieQueue = new PriorityQueue<>(10, new YearComparator(SortType.Ascending));
      movieSecondQueue = new PriorityQueue<>(10, new TwoFieldComparator(SortType.Ascending));
      IMovieDao = new MySqlMovieDao();
      populateArrayList(movieList);
      populateHashMap(movieList, movieHashMap);
      populateTreeMap(movieList, stringTreeMap);
      populatePriorityQueue(movieList, movieQueue);
      populatePriorityQueue(movieList, movieSecondQueue);
      menu();
   }

   private void populateArrayList(List<Movie> list) {
      list.add(new Movie(
            "The Fast and the Furious",
            2001,
            207.3,
            "Rob Cohen",
            "Vin Diesel"
      ));
      list.add(new Movie(
            "2 Fast 2 Furious",
            2003,
            236.4,
            "John Singleton",
            "Paul Walker"
      ));
      list.add(new Movie(
            "The Fast and the Furious: Tokyo Drift",
            2006,
            158.9,
            "Justin Lin",
            "Sung Kang"
      ));
      list.add(new Movie(
            "Fast & Furious",
            2009,
            360.4,
            "Justin Lin",
            "Vin Diesel"
      ));
      list.add(new Movie(
            "Fast Five",
            2011,
            626.1,
            "Justin Lin",
            "Vin Diesel"
      ));
      list.add(new Movie(
            "Fast & Furious 6",
            2013,
            788.7,
            "Justin Lin",
            "Vin Diesel"
      ));
      list.add(new Movie(
            "Furious 7",
            2015,
            1516.0,
            "James Wan",
            "Vin Diesel"
      ));
      list.add(new Movie(
            "The Fate of the Furious",
            2017,
            1239.0,
            "F. Gary Gray",
            "Vin Diesel"
      ));
      list.add(new Movie(
            "F9",
            2021,
            726.2,
            "Justin Lin",
            "Vin Diesel"
      ));
      list.add(new Movie(
            "Toy Story",
            1995,
            373.0,
            "John Lasseter",
            "Tom Hanks"
      ));
   }

   private void populateHashMap(List<Movie> list, Map<Integer, Movie> map) {
      int index = 0;
      for (Movie m : list) {
         map.put(index++, m);
      }
   }

   private void populateTreeMap(List<Movie> list, Map<String, String> map) {
      list.forEach(movie -> map.put(movie.getTitle(), movie.getLeadActor()));
   }

   private void populatePriorityQueue(List<Movie> list, final Queue<Movie> queue) {
      queue.addAll(list);
   }

   private void menu() {
      final String MENU = "\n*** MENU ***\n"
            + "1. Display all Movies\n"
            + "2. Find Movie by Key\n"
            + "3. Display all Actors in TreeMap\n"
            + "4. PriorityQueue Sequence Simulation\n"
            + "5. PriorityQueue Two-Field Comparison\n"
            + "6. Display all Movies from Database\n"
            + "7. Find Movie by ID from Database\n"
            + "8. Delete Movie by ID from Database\n"
            + "9. Add Movie to Database\n"
            + "10. Filter Movies from Database\n"
            + "11. Display all Movies from Database as JSON\n"
            + "12. Find Movie by ID from Database as JSON\n"
            + "13. Exit\n"
            + "Enter Option [1, 8]: ";

      final int DISPLAY_LIST     = 1;
      final int FIND_IN_MAP      = 2;
      final int DISPLAY_MAP      = 3;
      final int PRIORITY_SEQUENCE = 4;
      final int PRIORITY_COMPARISON = 5;
      final int DISPLAY_DB       = 6;
      final int FIND_IN_DB       = 7;
      final int DELETE_IN_DB     = 8;
      final int ADD_IN_DB        = 9;
      final int FILTER_DB        = 10;
      final int DISPLAY_DB_JSON  = 11;
      final int FIND_IN_DB_JSON  = 12;
      final int EXIT             = 13;

      int option = 0;

      do {
         System.out.print("\n" + MENU);
         try {
            String usersInput = KB.nextLine();
            option = Integer.parseInt(usersInput);
            switch (option) {
               case DISPLAY_LIST:
                  System.out.println("\nDisplay ALL Movies");
                  displayList(movieList);
                  break;

               case FIND_IN_MAP:
                  System.out.println("\nFind Movie by Key");
                  findByKey();
                  break;

               case DISPLAY_MAP:
                  System.out.println("\nDisplay all Actors in TreeMap");
                  displayMap(stringTreeMap);
                  break;

               case PRIORITY_SEQUENCE:
                  System.out.println("\nPriorityQueue Sequence Simulation");
                  addThirdPriorityElements(movieQueue);
                  addSecondPriorityElements(movieQueue);
                  removeAndDisplayElement(movieQueue);
                  addTopPriorityElement(movieQueue);
                  removeAllElements(movieQueue);
                  break;

               case PRIORITY_COMPARISON:
                  System.out.println("\nPriorityQueue Two-Field Comparison");
                  displayQueue(movieSecondQueue);
                  break;

               case DISPLAY_DB:
                  System.out.println("\nFind All Movies");
                  displayDb();
                  break;

               case FIND_IN_DB:
                  System.out.println("\nFind Movie by ID");
                  findById();
                  break;

               case DELETE_IN_DB:
                  System.out.println("\nDelete Movie by ID");
                  deleteById();
                  break;

               case ADD_IN_DB:
                  System.out.println("\nAdd Movie to Database");
                  addMovie();
                  break;

               case FILTER_DB:
                  System.out.println("\nFilter Movies");
                  filterMoviesMenu();
                  break;

               case DISPLAY_DB_JSON:
                  System.out.println("\nDisplay All Movies as JSON");
                  displayDbJson();
                  break;

               case FIND_IN_DB_JSON:
                  System.out.println("\nFind Movie by ID as JSON");
                  findByIdJson();
                  break;

               case EXIT:
                  System.out.println("\nExit Menu option chosen");
                  break;

               default:
                  System.out.print("\nInvalid option - please enter number in range");
                  break;
            }
         } catch (InputMismatchException | NumberFormatException e) {
            System.out.print("\nInvalid option - please enter number in range");
         }
      } while (option != EXIT);
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

   public void displayList(List<Movie> list) {
      printTableTitle("Movie ArrayList");
      printTableHeader(null);
      list.forEach(Movie::display);
   }

   private void findByKey() {
      String keyStr = null;
      int key = 0;

      printTableTitle("Find by Key");

      try {
         System.out.print("Enter a key: ");
         keyStr = KB.nextLine();
         key = Integer.parseInt(keyStr);

         Movie movie = movieHashMap.get(key);
         if (movie != null) {
            printTableHeader("*** Movie Found ***");
            movie.display();
         } else {
            System.out.println("Key does not match any Movie");
         }

      } catch (InputMismatchException | NumberFormatException e) {
         System.out.print("Invalid Input - Key has to a whole number");
      }
   }

   private <K, V> void displayMap(Map<K, V> map) {
      int tab1 = -40;
      map.forEach((key, value) -> System.out.printf("Key: %" + tab1 + "s Value: " + value + "%n", key));
   }

   private void addThirdPriorityElements(Queue<Movie> queue) {
      queue.add(new Movie(
            "The Jungle Book",
            1967,
            378,
            "Wolfgang Reitherman",
            "Phil Harris"
      ));

      queue.add(new Movie(
            "Home Alone",
            1990,
            476.7,
            "Chris Columbus",
            "Macaulay Culkin"
      ));
   }

   private void addSecondPriorityElements(Queue<Movie> queue) {
      queue.add(new Movie(
            "I Am Legend",
            2007,
            585.4,
            "Francis Lawrence",
            "Will Smith"
      ));

      queue.add(new Movie(
            "The Dark Knight",
            2008,
            1005,
            "Christopher Nolan",
            "Christian Bale"
      ));
   }

   private void removeAndDisplayElement(Queue<Movie> queue) {
      printTableHeader("*** Removing Element ***");
      queue.remove().display();
   }

   private void addTopPriorityElement(Queue<Movie> queue) {
      queue.add(new Movie(
            "The Matrix Resurrections",
            2021,
            156.6,
            "Lana Wachowski",
            "Keanu Reeves"
      ));
   }

   private void removeAllElements(Queue<Movie> queue) {
      printTableHeader("*** Clearing PriorityQueue ***");
      while (!queue.isEmpty())
         queue.remove().display();
   }

   private void displayQueue(Queue<Movie> queue) {
      printTableTitle("Movie PriorityQueue");
      queue.forEach(Movie::display);
   }

   private void displayDb() {
      try {
         System.out.println("\nGetting All Movie...");
         List<Pair<Integer, Movie>> movies = IMovieDao.findAllMovies();

         if (movies.isEmpty())
            System.out.println("No Movies found in Database");
         else {
            printTableTitle("Movies in Database");
            printTableHeader(null, true);
            movies.forEach(pair -> pair.getValue1().display(pair.getValue0()));
         }
      } catch (DaoException e) {
         System.out.println("Database Operation Failed! " + e);
      }
   }

   private void findById() {
      try {
         System.out.print("Enter Movie ID (number): ");
         String usersInput = KB.nextLine();
         int id = Integer.parseInt(usersInput);

         System.out.println("\nFinding Movie...\n");
         Pair<Integer, Movie> movie = IMovieDao.findMovieById(id);

         if (movie != null) {
            printTableHeader("Movie Found");
            movie.getValue1().display();
         }
         else
            System.out.println("Movie not found");
      } catch (InputMismatchException | NumberFormatException e) {
         System.out.print("Invalid input - ID must be a number");
      } catch (DaoException e) {
         System.out.println("Database Operation Failed! " + e);
      }
   }

   private void deleteById() {
      try {
         System.out.print("Enter Movie ID (number): ");
         String usersInput = KB.nextLine();
         int id = Integer.parseInt(usersInput);

         Pair<Integer, Movie> movie = IMovieDao.findMovieById(id);

         if (movie == null)
            System.out.println("Movie not found");
         else {
            System.out.println("\nDeleting Movie...\n");
            IMovieDao.deleteMovieById(id);
            System.out.println("Movie \"" + movie.getValue1().getTitle() + "\" (id: " + id + ") has been deleted!");
         }
      } catch (InputMismatchException | NumberFormatException e) {
         System.out.print("Invalid input - ID must be a number");
      } catch (DaoException e) {
         System.out.println("Database Operation Failed! " + e);
      }
   }

   private void addMovie() {
      try {
         System.out.print("Enter Movie Title: ");
         String title = KB.nextLine();

         System.out.print("Enter Movie Year: ");
         int year = Integer.parseInt(KB.nextLine());

         System.out.print("Enter Movie BoxOffice (in millions eg. €20.2): €");
         double boxOffice = Double.parseDouble(KB.nextLine());

         System.out.print("Enter Movie Director: ");
         String director = KB.nextLine();

         System.out.print("Enter Movie Lead Actor: ");
         String leadActor = KB.nextLine();

         Movie movie = new Movie(title, year, boxOffice, director, leadActor);

         System.out.println("\nAdding Movie...\n");
         IMovieDao.addMovie(movie);
         System.out.println("Movie \"" + title + "\" has been added!\n");
      } catch (InputMismatchException | NumberFormatException e) {
         System.out.print("Invalid input - BoxOffice and Year must be a number");
      } catch (DaoException e) {
         System.out.println("Database Operation Failed! " + e);
      }
   }

   private void displayDbJson() {
      try {
         System.out.println("\nGetting All Movie...");
         List<Pair<Integer, Movie>> movies = IMovieDao.findAllMovies();

         if (movies.isEmpty())
            System.out.println("No Movies found in Database");
         else {
            System.out.println("Movies in Database as JSON\n");
            GSON.toJson(movies, System.out);
         }
      } catch (DaoException e) {
         System.out.println("Database Operation Failed! " + e);
      }
   }

   private void findByIdJson() {
      try {
         System.out.print("Enter Movie ID (number): ");
         String usersInput = KB.nextLine();
         int id = Integer.parseInt(usersInput);

         System.out.println("\nFinding Movie...\n");
         Pair<Integer, Movie> movie = IMovieDao.findMovieById(id);

         if (movie != null) {
            System.out.println("Movie Found\n");
            GSON.toJson(movie, System.out);
         }
         else
            System.out.println("Movie not found");
      } catch (InputMismatchException | NumberFormatException e) {
         System.out.print("Invalid input - ID must be a number");
      } catch (DaoException e) {
         System.out.println("Database Operation Failed! " + e);
      }
   }

   private void filterMoviesMenu() {
      final String MENU = "\n*** MENU ***\n"
            + "1. Filter Movies using YearComparator in Ascending Order\n"
            + "2. Filter Movies using YearComparator in Descending Order\n"
            + "3. Filter Movies using TwoFieldComparator in Ascending Order\n"
            + "4. Filter Movies using TwoFieldComparator in Descending Order\n"
            + "5. Exit\n"
            + "Enter Option [1, 5]: ";

      final int YEAR_COMPARATOR_ASC       = 1;
      final int YEAR_COMPARATOR_DESC      = 2;
      final int TWO_FIELD_COMPARATOR_ASC  = 3;
      final int TWO_FIELD_COMPARATOR_DESC = 4;
      final int EXIT                      = 5;

      int option = 0;

      do {
         System.out.print("\n" + MENU);
         try {
            String usersInput = KB.nextLine();
            option = Integer.parseInt(usersInput);
            switch (option) {
               case YEAR_COMPARATOR_ASC:
                  System.out.println("\nFilter Movies using YearComparator (Ascending Order)");
                  filterMovies(new PairYearComparator(SortType.Ascending));
                  break;

               case TWO_FIELD_COMPARATOR_ASC:
                  System.out.println("\nFilter Movies using using YearComparator (Descending Order)");
                  filterMovies(new PairYearComparator(SortType.Descending));
                  break;

               case YEAR_COMPARATOR_DESC:
                  System.out.println("\nFilter Movies using using YearComparator (Ascending Order)");
                  filterMovies(new PairTwoFieldComparator(SortType.Ascending));
                  break;

               case TWO_FIELD_COMPARATOR_DESC:
                  System.out.println("\nFilter Movies using using YearComparator (Descending Order)");
                  filterMovies(new PairTwoFieldComparator(SortType.Descending));
                  break;

               case EXIT:
                  System.out.println("\nExit Menu option chosen");
                  break;

               default:
                  System.out.print("\nInvalid option - please enter number in range");
                  break;
            }
         } catch (InputMismatchException | NumberFormatException e) {
            System.out.print("\nInvalid option - please enter number in range");
         }
      } while (option != EXIT);
   }

   private void filterMovies(Comparator<Pair<Integer, Movie>> comparator) {
      try {
         System.out.println("\nFiltering Movies...");
         List<Pair<Integer, Movie>> movies = IMovieDao.findAllMovies();
         movies.sort(comparator);

         if (movies.isEmpty())
            System.out.println("No Movies found in Database");
         else {
            printTableTitle("Movies in Database Filtered");
            printTableHeader(null, true);
            movies.forEach(pair -> pair.getValue1().display(pair.getValue0()));
         }
      } catch (InputMismatchException | NumberFormatException e) {
         System.out.print("Invalid input - Year must be a number");
      } catch (DaoException e) {
         System.out.println("Database Operation Failed! " + e);
      }
   }
}
