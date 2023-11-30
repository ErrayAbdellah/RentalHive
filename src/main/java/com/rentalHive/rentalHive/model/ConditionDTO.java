package com.rentalHive.rentalHive.model;

import com.rentalHive.rentalHive.enums.State;

public class ConditionDTO {
    private Long id;
    private String description;
    private State state;
    private String body;

    public ConditionDTO() {
        this.description = description;
        this.state = state;
        this.body = body;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }}