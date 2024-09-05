package com.aymen.enchere.participant;

public class ParticipantCountDTO {
    private String firstname;
    private String lastname;
    private String gmail;
    private long participationCount;

    // Constructeurs, getters et setters
    public ParticipantCountDTO(String firstname, String lastname, String gmail, long participationCount) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.gmail = gmail;
        this.participationCount = participationCount;
    }

    // Getters et setters
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public long getParticipationCount() {
        return participationCount;
    }

    public void setParticipationCount(long participationCount) {
        this.participationCount = participationCount;
    }
}

