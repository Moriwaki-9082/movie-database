package com.example.moviedatabase.response;

public class MovieResponse {
    private String message;

    public MovieResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
