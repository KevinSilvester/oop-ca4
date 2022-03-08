package org.project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {
    private static final Scanner KB = new Scanner(System.in);
    private static List<Movie> movieList;

    public static void main( String[] args ) {
        App app = new App();
        app.start();
    }

    public void start() {
        System.out.println("Hello World");
        System.out.println("Project part 1 - CA5");
        movieList = new ArrayList<>();
        initialize(movieList);
        menu();
    }

    private void initialize(List<Movie> list) {
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
                    add("brian tee");
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

    private void menu() {
        final String MENU = "\n*** MENU ***\n"
                + "1. Display all Movies\n"
                + "2. Exit\n"
                + "Enter Option [1, 2]";

        final int DISPLAY_APP   = 1;
        final int EXIT          = 2;

        boolean valid = true;
        int option = 0;

        do {
            System.out.println("\n" + MENU);
            try {
                String usersInput = KB.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case DISPLAY_APP:
                        System.out.println("Display ALL Movies");
                        displayList(movieList);
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
            //sb.append(String.format("%" + titleTab + "s%-7d%-7.1f%-7s%n", m.getTitle(), m.getYear(), m.getBoxOffice(), m.getDirector()));
        }
        System.out.println(sb);
    }
}
