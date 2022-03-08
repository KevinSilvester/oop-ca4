package org.project;

import java.util.List;
import java.util.Objects;

public class Movie {
    private String title;
    private int year;
    private double boxOffice;
    private String director;
    private List<String> actors;

    public Movie(String title, int year, double boxOffice, String director, List<String> actors) {
        this.title = title;
        this.year = year;
        this.boxOffice = boxOffice;
        this.director = director;
        this.actors = actors;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public double getBoxOffice() {
        return boxOffice;
    }

    public String getDirector() {
        return director;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setBoxOffice(double boxOffice) {
        this.boxOffice = boxOffice;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return getYear() == movie.getYear() && getTitle().equals(movie.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getYear());
    }
}
