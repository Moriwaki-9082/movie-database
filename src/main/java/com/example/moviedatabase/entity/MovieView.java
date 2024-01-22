package com.example.moviedatabase.entity;

import java.util.Objects;

public class MovieView {
    private Integer id;
    private String name;
    private String releaseYear;
    private String country;

    public MovieView(Integer id, String name, String releaseYear, String country) {
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

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String price) {
        this.country = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieView movie = (MovieView) o;
        return id == movie.id && Objects.equals(name, movie.name) && Objects.equals(releaseYear, movie.releaseYear) && Objects.equals(country, movie.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, releaseYear, country);
    }

}
