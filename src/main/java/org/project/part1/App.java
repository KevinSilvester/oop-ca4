package org.project.part1;

import org.project.part1.Comparators.MapKeyComparator;
import org.project.part1.Comparators.TwoFieldComparator;
import org.project.part1.Comparators.YearComparator;
import org.project.part1.Enumerators.SortType;

import java.util.*;


public class App {
    private static final Scanner KB = new Scanner(System.in);
    private static List<Movie> movieList;
    private static Map<Integer, Movie> movieHashMap;
    private static Map<String, List<String>> stringTreeMap;
    private static Queue<Movie> movieQueue;
    private static Queue<Movie> movieSecondQueue;

    public static void main( String[] args ) {
        App app = new App();
        app.start();
    }

    private void start() {
        System.out.println("Hello World");
        System.out.println("Project part 1 - CA5");
        movieList = new ArrayList<>();
        movieHashMap = new HashMap<>();
        stringTreeMap = new TreeMap<>(new MapKeyComparator(SortType.Ascending));
        movieQueue = new PriorityQueue<>(10, new YearComparator(SortType.Ascending));
        movieSecondQueue = new PriorityQueue<>(10, new TwoFieldComparator(SortType.Ascending));
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
               new ArrayList<String>(){{
                   add("Vin Diesel");
                   add("Paul Walker");
                   add("Michelle Rodriguez");
               }}
       ));
        list.add(new Movie(
                "2 Fast 2 Furious",
                2003,
                236.4,
                "John Singleton",
                new ArrayList<String>(){{
                    add("Paul Walker");
                    add("Michelle Rodriguez");
                }}
        ));
        list.add(new Movie(
                "The Fast and the Furious: Tokyo Drift",
                2006,
                158.9,
                "Justin Lin",
                new ArrayList<String>(){{
                    add("Sung Kang");
                    add("Lucas Black");
                    add("Nathalie Kelley");
                    add("Brian Tee");
                }}
        ));
        list.add(new Movie(
                "Fast & Furious",
                2009,
                360.4,
                "Justin Lin",
                new ArrayList<String>(){{
                    add("Vin Diesel");
                    add("Paul Walker");
                    add("Michelle Rodriguez");
                }}
        ));
        list.add(new Movie(
                "Fast Five",
                2011,
                626.1,
                "Justin Lin",
                new ArrayList<String>(){{
                    add("Vin Diesel");
                    add("Paul Walker");
                    add("Gal Gadot");
                }}
        ));
        list.add(new Movie(
                "Fast & Furious 6",
                2013,
                788.7,
                "Justin Lin",
                new ArrayList<String>(){{
                    add("Vin Diesel");
                    add("Dwayne Johnson");
                    add("Gal Gadot");
                }}
        ));
        list.add(new Movie(
                "Furious 7",
                2015,
                1516.0,
                "James Wan",
                new ArrayList<String>(){{
                    add("Vin Diesel");
                    add("Dwayne Johnson");
                    add("Michelle Rodriguez");
                }}
        ));
        list.add(new Movie(
                "The Fate of the Furious",
                2017,
                1239.0,
                "F. Gary Gray",
                new ArrayList<String>(){{
                    add("Vin Diesel");
                    add("Dwayne Johnson");
                    add("Michelle Rodriguez");
                }}
        ));
        list.add(new Movie(
                "F9",
                2021,
                726.2,
                "Justin Lin",
                new ArrayList<String>(){{
                    add("Vin Diesel");
                    add("Dwayne Johnson");
                    add("Michelle Rodriguez");
                    add("John Cena");
                }}
        ));
        list.add(new Movie(
                "Toy Story",
                1995,
                373.0,
                "John Lasseter",
                new ArrayList<String>(){{
                    add("Tom Hanks");
                    add("Wallace Shawn");
                    add("Laurie Metcalf");
                }}
        ));
    }

    private void populateHashMap(List<Movie> list, Map<Integer, Movie> map) {
        int index = 0;
        for (Movie m : list) {
            map.put(index++, m);
        }
    }

    private void populateTreeMap(List<Movie> list, Map<String, List<String>> map) {
        list.forEach(movie -> map.put(movie.getTitle(), movie.getActors()));
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
                + "6. Exit\n"
                + "Enter Option [1, 6]";

        final int DISPLAY_LIST = 1;
        final int FIND_IN_MAP  = 2;
        final int DISPLAY_MAP  = 3;
        final int PRIORITY_SEQUENCE = 4;
        final int PRIORITY_COMPARISON = 5;
        final int EXIT         = 6;

        int option = 0;

        do {
            System.out.println("\n" + MENU);
            try {
                String usersInput = KB.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case DISPLAY_LIST:
                        System.out.println("Display ALL Movies");
                        displayList(movieList);
                        break;

                    case FIND_IN_MAP:
                        System.out.println("Find Movie by Key");
                        findByKey();
                        break;

                    case DISPLAY_MAP:
                        System.out.println("Display all Actors in TreeMap");
                        displayMap(stringTreeMap);
                        break;

                    case PRIORITY_SEQUENCE:
                        System.out.println("PriorityQueue Sequence Simulation");
                        addThirdPriorityElements(movieQueue);
                        addSecondPriorityElements(movieQueue);
                        removeAndDisplayElement(movieQueue);
                        addTopPriorityElement(movieQueue);
                        removeAllElements(movieQueue);
                        break;

                    case PRIORITY_COMPARISON:
                        System.out.println("PriorityQueue Two-Field Comparison");
                        displayQueue(movieSecondQueue);
                        break;

                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;

                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);
    }

    private void printTableTitle(String title) {
        System.out.println(
                "\n============================================\n"
                + "\t\t\t" + title + "\n"
                + "============================================");
    }

    private void printTableHeader(String optionalMessage) {
        int titleTab = -40;
        int yearTab = -7;
        int boxOfficeTab = -12;
        int directorTab = -20;
        int actorsTab = -18;

        if (optionalMessage != null)
            System.out.println("\n" + optionalMessage);

        System.out.printf(
                "%" + titleTab + "s" +
                "%" + yearTab + "s" +
                "%" + boxOfficeTab + "s" +
                "%" + directorTab + "s" +
                "%" + actorsTab + "s%n",
                "Title", "Year", "BoxOffice", "Director", "Actors");
    }

    private void displayList(List<Movie> list) {
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
                new ArrayList<String>() {{
                    add("Phil Harris");
                    add("Sebastian Cabot");
                    add("George Sanders");
                }}));

        queue.add(new Movie(
                "Home Alone",
                1990,
                476.7,
                "Chris Columbus",
                new ArrayList<String>() {{
                    add("Macaulay Culkin");
                    add("Joie Pesci");
                    add("Daniel Stern");
                }}));
    }

    private void addSecondPriorityElements(Queue<Movie> queue) {
        queue.add(new Movie(
                "I Am Legend",
                2007,
                585.4,
                "Francis Lawrence",
                new ArrayList<String>() {{
                    add("Will Smith");
                    add("Alice Braga");
                    add("Charlie Tahan");
                }}));

        queue.add(new Movie(
                "The Dark Knight",
                2008,
                1005,
                "Christopher Nolan",
                new ArrayList<String>() {{
                    add("Christian Bale");
                    add("Heath Ledger");
                    add("Michael Caine");
                }}));
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
                new ArrayList<String>() {{
                    add("Keanu Reeves");
                    add("Laurence Fishburne");
                    add("Carrie-Anne Moss");
                }}));
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
}
