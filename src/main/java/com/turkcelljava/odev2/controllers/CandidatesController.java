package com.turkcelljava.odev2.controllers;

import com.turkcelljava.odev2.Request;
import com.turkcelljava.odev2.business.abstracts.CandidateService;
import com.turkcelljava.odev2.entities.Candidate;
import com.turkcelljava.odev2.entities.Language;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
public class CandidatesController {
    CandidateService candidateService;

    public CandidatesController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    // Get all candidates
    @GetMapping
    List<Candidate> get() {
        return candidateService.getAll();
    }

    //Add candidate
    // To do: Separate error message displayed if it cant add
    @PostMapping()
    String add(@RequestBody Candidate candidate) {
        Candidate addedCandidate = candidateService.add(candidate);
        if (addedCandidate == null) {
            return "Duplicate entry or Empty Language List!"; // Solve this
        } else {
            return "Candidate added: " + addedCandidate.getFirstName() + " with languages " +
                    addedCandidate.getLanguageList().toString();
        }
    }

    // Get individual Candidate by its id
    @GetMapping("{id}")
    public Candidate getCandidateFromId(@PathVariable("id") int id) {
        return candidateService.getCandidateById(id);
    }

    //Updating candidate
    @PutMapping("{id}")
    public String putCandidate(@RequestBody Candidate candidate, @PathVariable("id") int id) {
        if (candidateService.updateCandidate(id, candidate)) {
            return "Updated candidate with id " + id;
        } else return "Could not update the candidate!";
    }

    @GetMapping("{id}/languages")
    public List<Language> getLanguagesOfCandidate(@PathVariable("id") int id) {
        return candidateService.getCandidateLanguageList(id);
    }

    // Add language to candidate
    @PostMapping("{id}/languages")
    public String addLanguageToCandidate(@RequestBody Request language, @PathVariable("id") int id) {
        if (candidateService.addLanguage(id, language.getName())) {
            return "Added language " + language.getName() + " to the userId: " + id;
        } else return "Could not add language. Duplicate entry!";
    }

    //Delete language of candidate
    @DeleteMapping("{id}/languages/{languageId}")
    public String deleteLanguageFromCandidate(@PathVariable("id") int id, @PathVariable("languageId") int languageId) {
        candidateService.deleteLanguage(id, languageId);
        return "Deleted language with id " + languageId;
    }

    //Update language of candidate
    @PutMapping("{id}/languages/{languageId}")
    public String updateLanguageOfCandidate(@RequestBody Language language, @PathVariable("id") int id, @PathVariable("languageId") int languageId) {
        if (!candidateService.updateLanguage(id, languageId, language)) {
            return "Language already present in the list!";
        }
        return "Updated the language of user id " + id;
    }
}
