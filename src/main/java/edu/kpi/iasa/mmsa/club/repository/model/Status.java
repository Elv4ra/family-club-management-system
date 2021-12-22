package edu.kpi.iasa.mmsa.club.repository.model;


public enum Status {
    INPROCESS ("In Process"),
    ACCEPTED ("Accepted"),
    REJECTED ("Rejected"),
    CLOSED ("Closed");

    private String code;

    private Status(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
