package com.techbow.i18nservice;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "i18nproperties")
public class I18nProperties {
    private String identifier;
    private String name;
    private String email;

    private Map<String, String> identifiers;
    private Map<String, String> names;
    private Map<String, String> emails;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, String> getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(Map<String, String> identifiers) {
        this.identifiers = identifiers;
    }

    public Map<String, String> getNames() {
        return names;
    }

    public void setNames(Map<String, String> names) {
        this.names = names;
    }

    public Map<String, String> getEmails() {
        return emails;
    }

    public void setEmails(Map<String, String> emails) {
        this.emails = emails;
    }
}
