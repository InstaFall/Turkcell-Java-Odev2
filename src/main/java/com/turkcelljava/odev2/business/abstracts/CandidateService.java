package com.turkcelljava.odev2.business.abstracts;

import com.turkcelljava.odev2.entities.Candidate;
import com.turkcelljava.odev2.entities.Language;

import java.util.List;

public interface CandidateService {
    List<Candidate> getAll();
    Candidate getCandidateById(int id);
    Candidate add(Candidate candidate);
    void remove(Candidate candidate);
    void removeById(int id);
    boolean updateCandidate(int candidateId, Candidate candidate);
    boolean addLanguage(int candidateId, Language language);
    boolean addLanguage(int candidateId, String language);
    void deleteLanguage(int candidateId, int languageId);
    boolean updateLanguage(int candidateId, int languageIdToReplace, Language languageToPut);
    List<Language> getCandidateLanguageList(int candidateId);
}
