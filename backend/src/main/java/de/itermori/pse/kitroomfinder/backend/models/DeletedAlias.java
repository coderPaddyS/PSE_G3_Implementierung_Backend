package de.itermori.pse.kitroomfinder.backend.models;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Provides a model for the entity DeletedAlias.
 * Aliases that were previously stored in the database and got deleted become deleted aliases.
 *
 * @author Lukas Zetto
 * @author Adriano Castro
 * @version 1.0
 */
@Entity
public class DeletedAlias {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "mapID", nullable = false)
    private Integer mapID;

    @Column(name = "version", nullable = false)
    private int version;

    /**
     * The standard constructor of the model.
     */
    public DeletedAlias() {
        // Hibernate requires an explicitly written standard constructor.
    }

    /**
     * The constructor for the initialization of a deleted alias entity.
     *
     * @param name      The name of the deleted alias.
     * @param mapID     The mapID for which the deleted alias served as an additional description.
     * @param version   The version of the deleted alias concerning the database.
     */
    public DeletedAlias(String name, Integer mapID, int version) {
        this.name = name;
        this.mapID = mapID;
        this.version = version;
    }

    /**
     * Getter method for the id of the deleted alias.
     *
     * @return  The id of the deleted alias.
     */
    public Long getId() {
        return id;
    }

    /**
     * Getter method for the name of the deleted alias.
     *
     * @return  The name of the deleted alias.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for the name of the deleted alias.
     *
     * @param name  The new name to set for the deleted alias.
     */
    public void setName(String name) {
        // Hibernate requires the setter for reading values from the database.
        this.name = name;
    }

    /**
     * Getter method for the version of the deleted alias.
     *
     * @return  The version of the deleted alias.
     */
    public int getVersion() {
        return version;
    }

    /**
     * Setter method for the version of the deleted alias.
     *
     * @param version  The new version to set for the deleted alias.
     */
    public void setVersion(int version) {
        // Hibernate requires the setter for reading values from the database.
        this.version = version;
    }

    /**
     * Getter method for the mapID of the deleted alias.
     *
     * @return  The mapID of the deleted alias.
     */
    public Integer getMapID() {
        return mapID;
    }

    /**
     * Setter method for the mapID of the deleted alias.
     *
     * @param mapID  The new mapID to set for the deleted alias.
     */
    public void setMapID(Integer mapID) {
        // Hibernate requires the setter for reading values from the database.
        this.mapID = mapID;
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
     * Two {@link DeletedAlias} models are equal, if their id is equal.
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
        DeletedAlias deletedAlias = (DeletedAlias) o;
        return getId().equals(deletedAlias.getId());
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
