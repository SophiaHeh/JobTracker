package com.tnite.jobwinner.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SWE extends GeneralJob{
    private List<String> languages;

    public SWE(String jobTitle, String description, String location, LocalDate applicationDate,
        Company company, Person person, double salary, Status jobStatus, Type jobType){
        super(jobTitle, description, location, applicationDate, company, person, salary, jobStatus, jobType);
        this.languages = new ArrayList<>();
    }

    public void addLanguage(String language) {
        if (language != null && !language.trim().isEmpty()) {
            this.languages.add(language);
        }
    }

    public List<String> getLanguages() {
        return new ArrayList<>(languages);
    }

    @Override
    public String displayDetailedInfo() {
        String baseInfo = super.displayDetailedInfo();
        String languageInfo = languages.isEmpty() ? "" : " | Languages: " + String.join(", ", languages);
        return baseInfo + languageInfo;
    }

}
