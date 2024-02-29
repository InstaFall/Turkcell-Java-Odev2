package com.turkcelljava.odev2.business.abstracts;

import com.turkcelljava.odev2.entities.Language;
import com.turkcelljava.odev2.entities.Language;

import java.util.List;

public interface LanguageService {
    List<Language> getAll();
    Language getLanguageById(int id);
    Language add(Language language);
    Language add(String language);
    void delete(Language language);
    void deleteById(int id);
    boolean updateLanguage(int languageId, Language language);
    boolean updateLanguage(int languageId, String language);
}
