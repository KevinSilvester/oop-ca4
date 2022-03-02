package org.project;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main( String[] args ) {
        App app = new App();
        app.start();
    }

    public void start() {
        System.out.println("Hello World");
        System.out.println("Project part 1 - CA5");
        List<Movie> movieList = new ArrayList<>();
        initialize(movieList);
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
}
