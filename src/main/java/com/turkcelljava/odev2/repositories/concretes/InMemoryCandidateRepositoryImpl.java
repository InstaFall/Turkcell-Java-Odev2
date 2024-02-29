package com.turkcelljava.odev2.repositories.concretes;

import com.turkcelljava.odev2.entities.Candidate;
import com.turkcelljava.odev2.entities.Language;
import com.turkcelljava.odev2.repositories.abstracts.InMemoryCandidateRepository;
import com.turkcelljava.odev2.repositories.abstracts.InMemoryLanguageRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class InMemoryCandidateRepositoryImpl implements InMemoryCandidateRepository {
    List<Candidate> candidateList;
    InMemoryLanguageRepository inMemoryLanguageRepository;

    public InMemoryCandidateRepositoryImpl(InMemoryLanguageRepository inMemoryLanguageRepository) {
        this.inMemoryLanguageRepository = inMemoryLanguageRepository;

        List<Language> list = (inMemoryLanguageRepository.getAll());
        candidateList = new ArrayList<Candidate>();
        // dummy candidates to begin with
        candidateList.add(new Candidate(0, "Can", "Akıllı", new ArrayList<>(Arrays.asList(list.get(1)))));
        candidateList.add(new Candidate(1, "John", "Doe", new ArrayList<>(Arrays.asList(list.get(0)))));
        candidateList.add(new Candidate(2, "Buse", "Deniz", new ArrayList<>(Arrays.asList(list.get(3)))));
        candidateList.add(new Candidate(3, "Tolga", "Yılmaz", new ArrayList<>(Arrays.asList(list.get(4)))));
    }

    @Override
    public List<Candidate> getAll() {
        return Collections.unmodifiableList(candidateList);
    }

    @Override
    public Candidate getCandidateById(int id) {
        int index = -1;
        for (int i = 0; i < candidateList.size(); i++) {
            if (candidateList.get(i).getId() == id) {
                index = i;
                break;
            }
        }
        return candidateList.get(index);
    }

    @Override
    public Candidate add(Candidate candidate) {
        candidateList.add(candidate);
        return candidate;
    }

    @Override
    public void delete(Candidate candidate) {
        candidateList.remove(candidate);
    }

    @Override
    public void deleteById(int id) {
        candidateList.removeIf(el -> el.getId() == id);
    }

    @Override
    public boolean update(int id, Candidate candidate) {
        for (int i = 0; i < candidateList.size(); i++) {
            if (candidateList.get(i).getId() == id) {
                candidateList.set(i, candidate);
                return true;
            }
        }
        return false;
    }

    @Override
    public void addLanguageToLanguages(String language) {
        inMemoryLanguageRepository.add(language);
    }

    @Override
    public void addLanguageToCandidate(int candidateId, Language language) {
        getCandidateById(candidateId).addLanguage(language);
    }

    @Override
    public void addLanguageToCandidate(int candidateId, String language) {
        int languageId = inMemoryLanguageRepository.findLanguageIdByName(language);
        if (languageId == -1)
            languageId = inMemoryLanguageRepository.generateId();
        getCandidateById(candidateId).addLanguage(new Language(languageId, language));
    }

    @Override
    public void addLanguageToCandidateById(int candidateId, int languageId) {
        getCandidateById(candidateId).addLanguageById(languageId);
    }

    @Override
    public void deleteLanguageFromCandidate(int candidateId, int languageId) {
        getCandidateById(candidateId).removeLanguageById(languageId);
    }

    @Override
    public void deleteLanguageFromCandidateById(int candidateId, int languageId) {
        for (Candidate candidate : candidateList) {
            if (candidate.getId() == candidateId) {
                candidate.removeLanguageById(languageId);
                return;
            }
        }
    }

    // If languages are set and fixed in the database these are useless??
    @Override
    public void updateLanguageOfCandidate(int candidateId, int languageIdToReplace, Language languageToPut) {
        for (Candidate candidate : candidateList) {
            if (candidate.getId() == candidateId) {
                candidate.updateLanguage(languageIdToReplace, languageToPut);
                break;
            }
        }
    }

    @Override
    public void updateLanguageOfCandidateById(int candidateId, int languageId) {

    }
}
