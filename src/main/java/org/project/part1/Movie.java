package org.project.part1;

import java.util.List;
import java.util.Objects;

public class Movie implements Comparable<Movie> {
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

    public void display() {
        StringBuilder sb = new StringBuilder();
        int titleTab = -40;
        int yearTab = -7;
        int boxOfficeTab = -12;
        int directorTab = -20;
        int actorsTab = -18;

        sb.append(String.format("%" + titleTab + "s", this.getTitle()));
        sb.append(String.format("%" + yearTab + "d", this.getYear()));
        sb.append(String.format("%" + boxOfficeTab + ".1f", this.getBoxOffice()));
        sb.append(String.format("%" + directorTab + "s", this.getDirector()));
        sb.append(String.format("%" + actorsTab + "s", this.getActors()));
        sb.append(String.format("%n"));
        System.out.print(sb);
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

    @Override
    public int compareTo(Movie o) {
        return this.getTitle().compareTo(o.getTitle());
    }
}
