package de.itermori.pse.kitroomfinder.backend.models;

import java.util.Objects;
import javax.persistence.Column;
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

    @Column(name = "mapID", nullable = false)
    private Integer mapID;

    @Column(name = "mapObject", nullable = false)
    private String mapObject;

    @Column(name = "suggester", nullable = false)
    private String suggester;

    private String voters;

    @Column(name = "posVotes", nullable = false)
    private Integer posVotes;

    @Column(name = "negVotes", nullable = false)
    private Integer negVotes;

    // Hibernate requires an explicitly written standard constructor.
    public AliasSuggestion() {
    }

    public AliasSuggestion(String name, Integer mapID, String mapObject, String suggester) {
        this.name = name;
        this.mapID = mapID;
        this.mapObject = mapObject;
        this.suggester = suggester;
        this.posVotes = 0;
        this.negVotes = 0;
        this.voters = suggester;
    }

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

    public Integer getMapID() {
        return mapID;
    }

    // Hibernate requires the setter for reading values from the database.
    private void setMapID(Integer mapID) {
        this.mapID = mapID;
    }

    public String getMapObject() {
        return mapObject;
    }

    // Hibernate requires the setter for reading values from the database.
    private void setMapObject(String mapObject) {
        this.mapObject = mapObject;
    }

    public String getSuggester() {
        return suggester;
    }

    // Hibernate requires the setter for reading values from the database.
    private void setSuggester(String suggester) {
        this.suggester = suggester;
    }

    public String getVoters() {
        return voters;
    }

    // Hibernate requires the setter for reading values from the database.
    private void setVoters(String voters) {
        this.voters = voters;
    }

    public Integer getPosVotes() {
        return posVotes;
    }

    // Hibernate requires the setter for reading values from the database.
    private void setPosVotes(Integer posVotes) {
        this.posVotes = posVotes;
    }

    public Integer getNegVotes() {
        return negVotes;
    }

    // Hibernate requires the setter for reading values from the database.
    private void setNegVotes(Integer negVotes) {
        this.negVotes = negVotes;
    }

    public boolean addPosVote() {
        ++posVotes;
        return true;
    }

    public boolean addNegVote() {
        ++negVotes;
        return true;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "-" + this.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AliasSuggestion aliasSuggestion = (AliasSuggestion) o;
        return getId().equals(aliasSuggestion.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
    
}
