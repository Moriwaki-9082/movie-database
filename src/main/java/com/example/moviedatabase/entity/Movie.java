package com.example.moviedatabase.entity;

import java.util.Objects;

public class Movie {
    private Integer id;
    private String name;
    private int releaseYear;
    private String country;

    public Movie(Integer id, String name, int releaseYear, String country) {
        this.id = id;
        this.name = name;
        this.releaseYear = releaseYear;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id && Objects.equals(name, movie.name) && Objects.equals(releaseYear, movie.releaseYear) && Objects.equals(country, movie.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, releaseYear, country);
    }

}
