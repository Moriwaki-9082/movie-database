package com.example.moviedatabase.request;

import com.example.moviedatabase.entity.Movie;

public class MovieUpdateRequest {

    private int id;
    private String name;
    private int releaseYear;
    private String country;

    public MovieUpdateRequest(int id, String name, int releaseYear, String country) {
        this.id = id;
        this.name = name;
        this.releaseYear = releaseYear;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getCountry() {
        return country;
    }

    public Movie updateToMovie() {
        return new Movie(this.id,this.name,this.releaseYear,this.country);
    }
}
