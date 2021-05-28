package com.techbow.i18nservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class I18nRestController {
    @Autowired
    private I18nProperties i18nProperties;

    @GetMapping("/identifier/{languageCode}")
    public String getIdentifier(@PathVariable("languageCode") @NonNull String languageCode) {
        return i18nProperties.getIdentifiers().getOrDefault(languageCode.toUpperCase(), i18nProperties.getIdentifier());
    }

    @GetMapping("/name/{languageCode}")
    public String getName(@PathVariable("languageCode") @NonNull String languageCode){
        return i18nProperties.getNames().getOrDefault(languageCode.toUpperCase(), i18nProperties.getName());
    }

    @GetMapping("/email/{languageCode}")
    public String getRole(@PathVariable("languageCode") @NonNull String languageCode){
        return i18nProperties.getEmails().getOrDefault(languageCode.toUpperCase(), i18nProperties.getEmail());
    }

    @GetMapping("/identifier")
    public String getIdentifier() {
        return i18nProperties.getIdentifier();
    }

    @GetMapping("/name")
    public String getName() {
        return i18nProperties.getName();
    }

    @GetMapping("/email")
    public String getEmail(){
        return i18nProperties.getEmail();
    }
}
