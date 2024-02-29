package com.turkcelljava.odev2.business.concretes;

import com.turkcelljava.odev2.business.abstracts.CandidateService;
import com.turkcelljava.odev2.entities.Candidate;
import com.turkcelljava.odev2.entities.Language;
import com.turkcelljava.odev2.repositories.abstracts.InMemoryCandidateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService {
    InMemoryCandidateRepository inMemoryCandidateRepository;

    public CandidateServiceImpl(InMemoryCandidateRepository inMemoryCandidateRepository) {
        this.inMemoryCandidateRepository = inMemoryCandidateRepository;
    }

    @Override
    public List<Candidate> getAll() {
        return inMemoryCandidateRepository.getAll();
    }

    @Override
    public Candidate getCandidateById(int id) {
        return inMemoryCandidateRepository.getCandidateById(id);
    }

    @Override
    public Candidate add(Candidate candidateToAdd) {
        // Should this check be done here or in repository ??
        if (candidateToAdd.getLanguageList().isEmpty())
            return null;
        boolean exists = false;
        for (Candidate candidate : inMemoryCandidateRepository.getAll()) {
            if (candidate.getId() == candidateToAdd.getId() ||
                (   candidate.getFirstName().equals(candidateToAdd.getFirstName()) &&
                    candidate.getLastName().equals(candidateToAdd.getLastName()))
            ) return null;
        }
            return inMemoryCandidateRepository.add(candidateToAdd);
    }

    @Override
    public void remove(Candidate candidate) {
        inMemoryCandidateRepository.delete(candidate);
    }

    @Override
    public void removeById(int id) {
        inMemoryCandidateRepository.deleteById(id);
    }

    @Override
    public boolean updateCandidate(int candidateId, Candidate candidate) {
        return inMemoryCandidateRepository.update(candidateId, candidate);

    }

    @Override
    public boolean addLanguage(int candidateId, String languageToAdd) {
        boolean exists = false;
        for (Language language : inMemoryCandidateRepository.getCandidateById(candidateId).getLanguageList()) {
            if ( language.getName().equals(languageToAdd)) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            // Add new language to languages in the database/memory
            inMemoryCandidateRepository.addLanguageToLanguages(languageToAdd);
            inMemoryCandidateRepository.addLanguageToCandidate(candidateId,languageToAdd);
            return true;
        }
        else return false;
    }

    // Language operations
    @Override
    public boolean addLanguage(int candidateId, Language languageToAdd) {
        boolean exists = false;
        for (Language language : inMemoryCandidateRepository.getCandidateById(candidateId).getLanguageList()) {
            if (
                language.getId() == languageToAdd.getId() ||
                language.getName().equals(languageToAdd.getName())
            ) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            inMemoryCandidateRepository.addLanguageToCandidate(candidateId,languageToAdd);
            return true;
        }
        else return false;
    }

    @Override
    public void deleteLanguage(int candidateId, int languageId) {
        inMemoryCandidateRepository.deleteLanguageFromCandidate(candidateId, languageId);
    }

    public void removeLanguage(int candidateId, Language languageToRemove) {
        // Any logic, checks to add here???
        inMemoryCandidateRepository.deleteLanguageFromCandidate(candidateId, languageToRemove.getId());
    }

    @Override
    public boolean updateLanguage(int candidateId, int languageIdToReplace, Language languageToPut) {
        Candidate candidate = getCandidateById(candidateId);
        for (Language language: candidate.getLanguageList()) {
            if(language.getId() == languageToPut.getId() || language.getName().equals(languageToPut.getName()))
        return false;
        }
        inMemoryCandidateRepository.updateLanguageOfCandidate(candidateId, languageIdToReplace, languageToPut);
        return true;
    }

    @Override
    public List<Language> getCandidateLanguageList(int candidateId) {
        return inMemoryCandidateRepository.getCandidateById(candidateId).getLanguageList();
    }
}
