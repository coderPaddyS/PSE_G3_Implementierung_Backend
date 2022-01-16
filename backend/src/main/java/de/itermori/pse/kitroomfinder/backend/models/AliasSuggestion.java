package de.itermori.pse.kitroomfinder.backend.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AliasSuggestion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "suggester", nullable = false)
    private String suggester;

    @Column(name = "appMapID", nullable = false)
    private Integer appMapID;

    @ElementCollection
    private List<String> voters = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Hibernate requires the setter for reading values from the database.
    private void setName(String name) {
        this.name = name;
    }

    public String getSuggester() {
        return suggester;
    }

    // Hibernate requires the setter for reading values from the database.
    private void setSuggester(String suggester) {
        this.suggester = suggester;
    }

    public Integer getAppMapID() {
        return appMapID;
    }

    // Hibernate requires the setter for reading values from the database.
    private void setAppMapID(Integer appMapID) {
        this.appMapID = appMapID;
    }

    public List<String> getVoters() {
        return voters;
    }

    // Hibernate requires the setter for reading values from the database.
    private void setVoters(List<String> voters) {
        this.voters = voters;
    }
    
}
