package com.turkcelljava.odev2.dtos;

import com.turkcelljava.odev2.entities.Language;

import java.util.List;

public class CandidateDTO {
    private int id;
    private String firstName;
    private String lastName;
    private List<Language> languageList; // Assumes you want to expose languages as part of the candidate's data

    // Constructors, getters, and setters
    public CandidateDTO(int id, String firstName, String lastName, List<Language> languageList) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.languageList = languageList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Language> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(List<Language> languageList) {
        this.languageList = languageList;
    }
// Additional constructors, getters, and setters as needed
}