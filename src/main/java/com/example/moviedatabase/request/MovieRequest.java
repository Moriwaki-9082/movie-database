package com.example.moviedatabase.request;

import com.example.moviedatabase.entity.Movie;

public class MovieRequest {

    private int id;
    private String name;
    private int releaseYear;
    private String country;

    public MovieRequest(int id, String name, int releaseYear, String country) {
        this.id = id;
        this.name = name;
        this.releaseYear = releaseYear;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Movie convertToMovie() {
        return new Movie(null,this.name,this.releaseYear,this.country);
    }

}
