package com.teseus.grammer;

public class Developer {
    private String name;
    private String favorateLanguage;

    public Developer(String name, String favorateLanguage) {
        this.name = name;
        this.favorateLanguage = favorateLanguage;
    }

    public String getName() {
        return name;
    }

    public String getFavorateLanguage() {
        return favorateLanguage;
    }
}
