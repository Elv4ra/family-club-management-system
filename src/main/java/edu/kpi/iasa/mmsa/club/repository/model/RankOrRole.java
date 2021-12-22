package edu.kpi.iasa.mmsa.club.repository.model;

public enum RankOrRole {
    ROLE("Role"),
    RANK("Rank");

    private String code;

    private RankOrRole(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}