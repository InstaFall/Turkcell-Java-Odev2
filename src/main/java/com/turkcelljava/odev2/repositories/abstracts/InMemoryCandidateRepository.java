package com.turkcelljava.odev2.repositories.abstracts;

import com.turkcelljava.odev2.entities.Candidate;
import com.turkcelljava.odev2.entities.Language;

import java.util.List;

public interface InMemoryCandidateRepository {
    List<Candidate> getAll();

    Candidate getCandidateById(int id);

    Candidate add(Candidate candidate);

    void delete(Candidate candidate);

    void deleteById(int id);

    boolean update(int id, Candidate candidate);

    //Language related operations on candidates
    void addLanguageToCandidate(int candidateId, Language language);

    void addLanguageToCandidate(int candidateId, String language);

    void addLanguageToCandidateById(int candidateId, int languageId);

    void deleteLanguageFromCandidate(int candidateId, int languageId);

    void deleteLanguageFromCandidateById(int candidateId, int languageId);

    void updateLanguageOfCandidate(int candidateId, int languageIdToReplace, Language languageToPut);

    void updateLanguageOfCandidateById(int candidateId, int languageId);

}
