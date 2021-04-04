package com.techelevator.model;

import javax.validation.constraints.NotNull;

public class AddPhotoJSON {

    @NotNull (message = "Photo description required!")
    private String description;
    @NotNull (message = "At least one tag required!")
    private String tagsAsCSV;
    @NotNull (message = "Photo url required!")
    private String url;

    public String getUrl() {
        return url;
    }


    public String getDescription() {
        return description;
    }

    public String getTagsAsCSV() {
        return tagsAsCSV;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTagsAsCSV(String tagsAsCSV) {
        this.tagsAsCSV = tagsAsCSV;
    }
}

