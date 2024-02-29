package com.turkcelljava.odev2.repositories.abstracts;

import com.turkcelljava.odev2.entities.Language;

import java.util.List;

public interface InMemoryLanguageRepository {
    List<Language> getAll();

    boolean add(Language language);

    Language add(String language);

    boolean delete(Language language);

    boolean delete(int id);

    boolean update(Language languageToUpdate, Language languageToPut);

    boolean update(int id, Language language);

    boolean update(int id, String language);

    Language getLanguageById(int id);

    int findLanguageIdByName(String name);

    int generateId();

}
