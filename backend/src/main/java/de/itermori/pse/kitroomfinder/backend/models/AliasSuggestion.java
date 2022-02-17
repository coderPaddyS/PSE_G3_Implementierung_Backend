package de.itermori.pse.kitroomfinder.backend.models;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Provides a model for the entity {@link AliasSuggestion}.
 * Represents an alias suggestion for a specific {@link MapID}.
 *
 * @author Lukas Zetto
 * @author Adriano Castro
 * @version 1.0
 */
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

    /**
     * The standard constructor of the model.
     */
    public AliasSuggestion() {
        // Hibernate requires an explicitly written standard constructor.
    }

    /**
     * The constructor for the initialization of an alias entity.
     *
     * @param name      The name of the alias suggestion.
     * @param mapID     The mapID for which the alias suggestion should serve as an additional description.
     * @param mapObject The name of the mapID.
     * @param suggester The suggester of the alias.
     */
    public AliasSuggestion(String name, Integer mapID, String mapObject, String suggester) {
        this.name = name;
        this.mapID = mapID;
        this.mapObject = mapObject;
        this.suggester = suggester;
        this.posVotes = 0;
        this.negVotes = 0;
        this.voters = suggester;
    }

    /**
     * Getter method for the id of the alias suggestion.
     *
     * @return  The id of the alias suggestion.
     */
    public Long getId() {
        return id;
    }

    /**
     * Getter method for the name of the alias suggestion.
     *
     * @return  The name of the alias suggestion.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for the name of the alias suggestion.
     *
     * @param name  The new name to set for the alias suggestion.
     */
    private void setName(String name) {
        // Hibernate requires the setter for reading values from the database.
        this.name = name;
    }

    /**
     * Getter method for the mapID of the alias suggestion.
     *
     * @return  The mapID of the alias suggestion.
     */
    public Integer getMapID() {
        return mapID;
    }

    /**
     * Setter method for the mapID of the alias suggestion.
     *
     * @param mapID  The new mapID to set for the alias suggestion.
     */
    private void setMapID(Integer mapID) {
        // Hibernate requires the setter for reading values from the database.
        this.mapID = mapID;
    }

    /**
     * Getter method for the name of the mapID of the alias suggestion.
     *
     * @return  The name of the mapID of the alias suggestion.
     */
    public String getMapObject() {
        return mapObject;
    }

    /**
     * Setter method for the name of the mapID of the alias suggestion.
     *
     * @param mapObject  The new name of the mapID to set for the alias suggestion.
     */
    private void setMapObject(String mapObject) {
        // Hibernate requires the setter for reading values from the database.
        this.mapObject = mapObject;
    }

    /**
     * Getter for the suggester of the alias suggestion.
     *
     * @return  The suggester of the alias suggestion.
     */
    public String getSuggester() {
        return suggester;
    }

    /**
     * Setter method for the suggester of the alias suggestion.
     *
     * @param suggester  The new suggester to set for the alias suggestion.
     */
    private void setSuggester(String suggester) {
        // Hibernate requires the setter for reading values from the database.
        this.suggester = suggester;
    }

    /**
     * Getter for the voters of the alias suggestion.
     *
     * @return  The voters of the alias suggestion.
     */
    public String getVoters() {
        return voters;
    }

    /**
     * Setter method for the voters of the alias suggestion.
     *
     * @param voters  The new voters to set for the alias suggestion.
     */
    private void setVoters(String voters) {
        // Hibernate requires the setter for reading values from the database.
        this.voters = voters;
    }

    /**
     * Getter for the amount of positive votes of the alias suggestion.
     *
     * @return  The amount of positive votes of the alias suggestion.
     */
    public Integer getPosVotes() {
        return posVotes;
    }

    /**
     * Setter method for the amount of positive votes of the alias suggestion.
     *
     * @param posVotes  The new amount of positive votes to set for the alias suggestion.
     */
    private void setPosVotes(Integer posVotes) {
        // Hibernate requires the setter for reading values from the database.
        this.posVotes = posVotes;
    }

    /**
     * Getter for the amount of negative votes of the alias suggestion.
     *
     * @return  The amount of negative votes of the alias suggestion.
     */
    public Integer getNegVotes() {
        return negVotes;
    }

    /**
     * Setter method for the amount of negative votes of the alias suggestion.
     *
     * @param negVotes  The new amount of negative votes to set for the alias suggestion.
     */
    private void setNegVotes(Integer negVotes) {
        // Hibernate requires the setter for reading values from the database.
        this.negVotes = negVotes;
    }

    /**
     * Returns a string representation of the model.
     *
     * @return  The string representation of the model.
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "-" + this.getId();
    }

    /**
     * Defines the equals method of the model.
     * Two {@link AliasSuggestion} models are equal, if their id is equal.
     *
     * @param o The object to compare to.
     * @return  True if the model and the provided object are equal concerning
     *          their id, otherwise false.
     */
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

    /**
     * Defines the hashCode of the model.
     *
     * @return  The hashCode of the model.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
    
}
