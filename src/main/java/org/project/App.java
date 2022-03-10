package org.project;

import java.util.*;

public class App {
    private static final Scanner KB = new Scanner(System.in);
    private static List<Movie> movieList;
    private static Map<Integer, Movie> movieHashMap;
    private static Map<String, List<String>> treeMap;

    public static void main( String[] args ) {
        App app = new App();
        app.start();
    }

    public void start() {
        System.out.println("Hello World");
        System.out.println("Project part 1 - CA5");
        movieList = new ArrayList<>();
        movieHashMap = new HashMap<>();
        treeMap = new TreeMap<>(new ComparatorMapKey());
        populateArrayList(movieList);
        populateHashMap(movieList, movieHashMap);
        populateTreeMap(movieList, treeMap);
        menu();
    }

    private void populateArrayList(List<Movie> list) {
       list.add(new Movie(
               "The fast and the Furious",
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

    public void populateHashMap(List<Movie> list, Map<Integer, Movie> map) {
        int index = 0;
        for (Movie m : list) {
            map.put(++index ,m);
        }
    }

    public void populateTreeMap(List<Movie> list, Map<String, List<String>> map) {
        for (Movie m : list) {
            map.put(m.getTitle(), m.getActors());
        }
    }

    private void menu() {
        final String MENU = "\n*** MENU ***\n"
                + "1. Display all Movies\n"
                + "2. Find Movie by Key\n"
                + "3. Display all Actors in TreeMap\n"
                + "4. Exit\n"
                + "Enter Option [1, 4]";

        final int DISPLAY_LIST  = 1;
        final int FIND_IN_MAP   = 2;
        final int DISPLAY_MAP   = 3;
        final int EXIT          = 4;

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
                        displayMap(treeMap);
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

    private void displayList(List<Movie> list) {
        StringBuilder sb = new StringBuilder();
        int titleTab = -40;
        int yearTab = -7;
        int boxOfficeTab = -12;
        int directorTab = -16;
        int actorsTab = -18;

        System.out.println(
            "\n============================================\n"
            + "\t\t\tMovie ArrayList\n"
            + "============================================");
        System.out.printf(
                "%n" +
                "%" + titleTab + "s" +
                "%" + yearTab + "s" +
                "%" + boxOfficeTab + "s" +
                "%" + directorTab + "s" +
                "%" + actorsTab + "s%n",
                "Title", "Year", "BoxOffice", "Director", "Actors");

        for (Movie m: list) {
            sb.append(String.format("%" + titleTab + "s", m.getTitle()));
            sb.append(String.format("%" + yearTab + "d", m.getYear()));
            sb.append(String.format("%" + boxOfficeTab + ".1f", m.getBoxOffice()));
            sb.append(String.format("%" + directorTab + "s", m.getDirector()));
            sb.append(String.format("%" + actorsTab + "s", m.getActors()));
            sb.append(String.format("%n"));
        }
        System.out.println(sb);
    }

    private void findByKey() {
        StringBuilder sb = new StringBuilder();
        String keyStr = null;
        int titleTab = -40;
        int yearTab = -7;
        int boxOfficeTab = -12;
        int directorTab = -16;
        int actorsTab = -18;
        int key = 0;

        System.out.println(
                "\n============================================\n"
                + "\t\t\tFind by Key\n"
                + "============================================");

        try {
            System.out.print("Enter a key: ");
            keyStr = KB.nextLine();
            key = Integer.parseInt(keyStr);

            Movie movie = movieHashMap.get(key);
            if (movie != null) {
                System.out.println("*** Movie Found ***");
                System.out.printf(
                        "%" + titleTab + "s" +
                        "%" + yearTab + "s" +
                        "%" + boxOfficeTab + "s" +
                        "%" + directorTab + "s" +
                        "%" + actorsTab + "s%n",
                        "Title", "Year", "BoxOffice", "Director", "Actors");

                sb.append(String.format("%" + titleTab + "s", movie.getTitle()));
                sb.append(String.format("%" + yearTab + "d", movie.getYear()));
                sb.append(String.format("%" + boxOfficeTab + ".1f", movie.getBoxOffice()));
                sb.append(String.format("%" + directorTab + "s", movie.getDirector()));
                sb.append(String.format("%" + actorsTab + "s", movie.getActors()));
                sb.append(String.format("%n"));
                System.out.println(sb);
            } else {
                System.out.println("Key does not match any Movie");
            }

        } catch (InputMismatchException | NumberFormatException e) {
            System.out.print("Invalid Input - Key has to a whole number");
        }
    }

    private <K, V> void displayMap(Map<K, V> map) {
        int tab1 = -40;
        for (Map.Entry<K, V> entry: map.entrySet()) {
            System.out.printf("Key: %" + tab1 + "s Value: " + entry.getValue() + "%n", entry.getKey());
        }
    }
}
