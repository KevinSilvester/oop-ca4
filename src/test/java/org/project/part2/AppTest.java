package org.project.part2;

import org.junit.jupiter.api.Test;
import org.project.part2.DTOs.Movie;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest
{
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue( true );
    }

    @Test
    public void testDisplayList() {
        List<Movie> movieList = new ArrayList<>(2){{
            add(new Movie(
                  "The Fast and the Furious",
                  2001,
                  207.3,
                  "Rob Cohen",
                  "Vin Diesel"
            ));
            add(new Movie(
                  "2 Fast 2 Furious",
                  2003,
                  236.4,
                  "John Singleton",
                  "Paul Walker"
            ));
        }};
        App app = new App();
        app.displayList(movieList);
    }
}
