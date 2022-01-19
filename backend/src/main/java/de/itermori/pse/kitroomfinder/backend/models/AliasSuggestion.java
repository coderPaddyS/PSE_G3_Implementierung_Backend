package de.itermori.pse.kitroomfinder.backend.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

@Entity
public class AliasSuggestion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "mapID", nullable = false)
    private Integer mapID;

    @Column(name = "suggester", nullable = false)
    private String suggester;

    @ElementCollection(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private List<String> voters;

    @Column(name = "posVotes", nullable = false)
    private Integer posVotes;

    @Column(name = "negVotes", nullable = false)
    private Integer negVotes;

    // Hibernate requires an explicitly written standard constructor.
    public AliasSuggestion() {
    }

    public AliasSuggestion(String name, Integer mapID, String suggester) {
        this.name = name;
        this.mapID = mapID;
        this.suggester = suggester;
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

    public String getSuggester() {
        return suggester;
    }

    // Hibernate requires the setter for reading values from the database.
    private void setSuggester(String suggester) {
        this.suggester = suggester;
    }

    public List<String> getVoters() {
        return voters;
    }

    // Hibernate requires the setter for reading values from the database.
    private void setVoters(List<String> voters) {
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

    public boolean addVoter(String user) {
        return voters.add(user);
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
    
}
