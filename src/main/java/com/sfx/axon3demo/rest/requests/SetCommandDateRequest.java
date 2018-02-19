package com.sfx.axon3demo.rest.requests;

public class SetCommandDateRequest {
    private Long date;
    private String id;

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }
}
