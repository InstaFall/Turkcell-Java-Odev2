package com.turkcelljava.odev2.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Candidate {
    private int id;
    private String firstName;
    private String lastName;
    private List<Language> languageList = new ArrayList<Language>();

    public Candidate() {
    }

    public Candidate(int id, String firstName, String lastName, List<Language> languageList) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.languageList = languageList;
    }

    public Candidate(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
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
        return Collections.unmodifiableList(languageList);
    }

    public void setLanguageList(List<Language> languageList) {
        this.languageList = languageList;
    }

    public void addLanguage(Language languageToAdd) {
        languageList.add(languageToAdd);
    }

    // ById functions are to be used if Candidate class holds Ids of Languages instead of Language objects in the list
    public void addLanguageById(int languageId) {
        // Create Language instance from languageId ?
    }

    public void updateLanguage(Language languageToReplace, Language languageToPut) {
        languageList.set(languageList.indexOf(languageToReplace), languageToPut);
    }

    public void updateLanguage(int languageId, Language language) {
        for (int i = 0; i < languageList.size(); i++) {
            if (languageList.get(i).getId() == languageId) {
                languageList.set(i, language);
                break;
            }
        }
    }

    public void removeLanguage(Language languageToRemove) {
        languageList.remove(languageToRemove);
    }

    public void removeLanguageById(int languageId) {
        for (int i = 0; i < languageList.size(); i++) {
            if (languageList.get(i).getId() == languageId) {
                languageList.remove(i);
                break;
            }
        }
    }
}
