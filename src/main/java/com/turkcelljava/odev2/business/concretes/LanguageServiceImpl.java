package com.turkcelljava.odev2.business.concretes;

import com.turkcelljava.odev2.business.abstracts.LanguageService;
import com.turkcelljava.odev2.entities.Language;
import com.turkcelljava.odev2.repositories.abstracts.InMemoryLanguageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageServiceImpl implements LanguageService {
    InMemoryLanguageRepository inMemoryLanguageRepository;

    public LanguageServiceImpl(InMemoryLanguageRepository inMemoryLanguageRepository) {
        this.inMemoryLanguageRepository = inMemoryLanguageRepository;
    }

    @Override
    public List<Language> getAll() {
        return inMemoryLanguageRepository.getAll();
    }

    @Override
    public Language getLanguageById(int id) {
        return inMemoryLanguageRepository.getLanguageById(id);
    }

    @Override
    public Language add(Language languageToAdd) {
        // Checks
        for (Language language : inMemoryLanguageRepository.getAll()) {
            if (language.getId() == languageToAdd.getId() || //duplicate language
            language.getName().equals(languageToAdd.getName())){
                return null;
            }
        }
        inMemoryLanguageRepository.add(languageToAdd);
        return languageToAdd;
    }

    @Override
    public Language add(String languageToAdd) {
        // Checks
        for (Language language : inMemoryLanguageRepository.getAll()) {
            if ( language.getName().equals(languageToAdd)){
                return null;
            }
        }
        return inMemoryLanguageRepository.add(languageToAdd);
    }

    @Override
    public void delete(Language language) {
        inMemoryLanguageRepository.delete(language);
    }

    @Override
    public void deleteById(int id) {
        inMemoryLanguageRepository.delete(id);
    }

    @Override
    public boolean updateLanguage(int languageId, Language language) {
        return inMemoryLanguageRepository.update(languageId, language);
    }

    @Override
    public boolean updateLanguage(int languageId, String language) {
        for (Language language1 : inMemoryLanguageRepository.getAll()) {
            if( language1.getName().equals(language))
                return false;
        }
        return inMemoryLanguageRepository.update(languageId, language);
    }
}
