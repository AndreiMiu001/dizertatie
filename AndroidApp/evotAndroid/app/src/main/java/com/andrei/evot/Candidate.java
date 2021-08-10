package com.andrei.evot;

public class Candidate {

    private String name_;
    private String additionaInfo_;
    private String candidateId_;
    private boolean isChecked_;

    public Candidate(String name_, String additionaInfo_, String candidateId_) {
        this.name_ = name_;
        this.additionaInfo_ = additionaInfo_;
        this.candidateId_ = candidateId_;
        this.isChecked_ = false;
    }

    public String getName() {
        return name_;
    }

    public String getAdditionaInfo() {
        return additionaInfo_;
    }

    public String getCandidateId() {
        return candidateId_;
    }

    public boolean isChecked() {
        return isChecked_;
    }

    public void setChecked(boolean checked_) {
        isChecked_ = checked_;
    }
}
