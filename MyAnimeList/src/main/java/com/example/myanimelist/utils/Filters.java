package com.example.myanimelist.utils;

public class Filters {
    public static boolean checkEmail(String email) {
        return email.matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$");
    }
}
