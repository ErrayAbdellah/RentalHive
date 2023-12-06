package com.rentalHive.rentalHive.model;

import com.rentalHive.rentalHive.enums.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConditionDTO {
    private Long id;
    private String description;
    private State state;
    private String body;
    private Long contratId;
    public ConditionDTO(Long id, String description, String string, String body, Long contratId) {
        this.description = this.description;
        this.state = state;
        this.body = this.body;
        this.contratId = contratId;
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
    }

    public Long getContratId() {
    return this.id;
    }

    public void setContratId(Long id) {
        this.id =id;
    }
}