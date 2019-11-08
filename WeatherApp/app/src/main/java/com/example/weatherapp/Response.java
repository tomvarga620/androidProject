package com.example.weatherapp;

public class Response {

    public class Weather {
        private int id;
        private String main;
        private String description;
    }

    private String base;

    public class Main{
        private String temp;
    }

    public class Sys {
        private String country;
    }

    private String name;
}
