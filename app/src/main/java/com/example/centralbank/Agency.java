package com.example.centralbank;

import java.util.List;

public class Agency {
    private String city;
    private List<String> names;
    private String name;
    private String idVille;

    public Agency(String city, List<String> names, String name) {
        this.city = city;
        this.names = names;
        this.name = name;
    }

    public String getIdVille() {
        return idVille;
    }

    public void setIdVille(String idVille) {
        this.idVille = idVille;
    }

    public Agency(String city, String name) {
        this.city = city;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Agency() {
        // Default constructor required for Firebase database
    }

    public Agency(String city, List<String> names) {
        this.city = city;
        this.names = names;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }
}
