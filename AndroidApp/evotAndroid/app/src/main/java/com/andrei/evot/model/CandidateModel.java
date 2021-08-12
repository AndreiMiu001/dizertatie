package com.andrei.evot.model;

import java.io.Serializable;

public class CandidateModel implements Serializable {

    private String name;
    private String description;
    private int id;
    private boolean isChecked;

    public CandidateModel(String name, String description, int id) {
        this.name = name;
        this.description = description;
        this.id = id;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
