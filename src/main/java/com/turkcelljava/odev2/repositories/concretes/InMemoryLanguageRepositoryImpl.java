package com.turkcelljava.odev2.repositories.concretes;

import com.turkcelljava.odev2.entities.Language;
import com.turkcelljava.odev2.repositories.abstracts.InMemoryLanguageRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class InMemoryLanguageRepositoryImpl implements InMemoryLanguageRepository {
    List<Language> languages;
    public InMemoryLanguageRepositoryImpl() {
        // Add dummy languages to the list
        languages = new ArrayList<Language>();
        languages.add(new Language(1,"C"));
        languages.add(new Language(2,"C++"));
        languages.add(new Language(3,"Java"));
        languages.add(new Language(4,"C#"));
        languages.add(new Language(5,"Python"));
        languages.add(new Language(6,"Javascript"));
    }

    @Override
    public List<Language> getAll() {
        return Collections.unmodifiableList(languages);
    }

    @Override
    public boolean add(Language language) {
        return languages.add(language);
    }

    public int generateId() {
        return languages.get(languages.size()-1).getId() + 1;
    }
    @Override
    public Language add(String language) {
        Language addedLanguage = new Language(generateId(), language);
        languages.add(addedLanguage);
        return addedLanguage;
    }

    @Override
    public boolean delete(Language language) {
        return languages.remove(language);
    }

    @Override
    public boolean delete(int id) {
        return languages.removeIf(l -> l.getId() == id);
    }

    @Override
    public boolean update(Language languageToUpdate, Language languageToPut) {
        int indexToUpdate = languages.indexOf(languageToUpdate);
        if (indexToUpdate != -1) {
            languages.set(indexToUpdate, languageToPut);
            return true;
        }
        return false;
    }


    @Override
    public boolean update(int id, Language language) {
        for (int i = 0; i < languages.size(); i++) {
            if (languages.get(i).getId() == id) {
                languages.set(i, language);
                return true;
            }
        }
        return false;
    }

    public int findLanguageIdByName(String name) {
        for (Language language : languages) {
            if( language.getName().equals(name)){
                return language.getId();
            }
        }
        return -1;
    }
    @Override
    public boolean update(int id, String language) {
        int languageId = findLanguageIdByName(language);
        if(languageId == -1)
            return false;
        Language updatedLanguage = new Language(languageId, language);
        for (int i = 0; i < languages.size(); i++) {
            if (languages.get(i).getId() == id) {
                languages.set(i, updatedLanguage);
                return true;
            }
        }
        return false;
    }

    @Override
    public Language getLanguageById(int id) {
        for (Language language : languages) {
            if (language.getId() == id)
                return language;
        }
        return null;
    }
}
