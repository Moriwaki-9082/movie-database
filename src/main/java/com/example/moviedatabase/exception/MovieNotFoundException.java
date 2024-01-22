package com.example.moviedatabase.exception;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(String message) {
        super(message);
    }

    public MovieNotFoundException() {

    }
}
