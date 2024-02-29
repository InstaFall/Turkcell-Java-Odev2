package com.turkcelljava.odev2.controllers;

import com.turkcelljava.odev2.Request;
import com.turkcelljava.odev2.business.abstracts.LanguageService;
import com.turkcelljava.odev2.entities.Language;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/languages")
public class LanguagesController {
    LanguageService languageService;

    public LanguagesController(LanguageService languageService) {
        this.languageService = languageService;
    }

    // Get all languages
    @GetMapping
    List<Language> getAll() {
        return languageService.getAll();
    }

    //Add language with language object in request body
//    @PostMapping()
//    String add (@RequestBody String language){
//        Language addedLanguage = languageService.add(language);
//        if(addedLanguage == null) {
//            return "Duplicate entry";
//        }
//        else {
//            return "Language added: " + addedLanguage.getName();
//        }
//    }

    // Add language by its name
    @PostMapping()
    String add(@RequestBody Request language) {
        Language addedLanguage = languageService.add(language.getName());
        if (addedLanguage == null) {
            return "Duplicate entry";
        } else {
            return "Language added: " + addedLanguage.getName() + " with id " + addedLanguage.getId();
        }
    }

    // Get individual Language by its id
    @GetMapping("{id}")
    public Language getLanguageById(@PathVariable("id") int id) {
        return languageService.getLanguageById(id);
    }

    //Updating language
    @PutMapping("{id}")
    public String putLanguage(@RequestBody Request language, @PathVariable("id") int id) {
        if (languageService.updateLanguage(id, language.getName())) {
            return "Updated language with id " + id;
        } else return "Could not update the language!";
    }

    //Delete language
    @DeleteMapping("{id}")
    public String deleteLanguage(@PathVariable("id") int id) {
        languageService.deleteById(id);
        return "Deleted language with id: " + id;
    }
}
