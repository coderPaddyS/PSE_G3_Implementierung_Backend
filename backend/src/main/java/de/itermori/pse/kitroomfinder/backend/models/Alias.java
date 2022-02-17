package de.itermori.pse.kitroomfinder.backend.models;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Provides a model for the entity {@link Alias}.
 * Represents an alias for a specific {@link MapID}.
 *
 * @author Lukas Zetto
 * @author Adriano Castro
 * @version 1.0
 */
@Entity
public class Alias {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "mapID", nullable = false)
    private Integer mapID;

    @Column(name = "mapObject", nullable = false)
    private String mapObject;

    @Column(name = "version", nullable = false)
    private int version;


    /**
     * The standard constructor of the model.
     */
    public Alias() {
        // Hibernate requires an explicitly written standard constructor.
    }

    /**
     * The constructor for the initialization of an alias entity.
     *
     * @param name      The name of the alias.
     * @param mapID     The mapID for which the alias should serve as an additional description.
     * @param mapObject The name of the mapID.
     * @param version   The version of the alias concerning the database.
     */
    public Alias(String name, Integer mapID, String mapObject, int version) {
        this.name = name;
        this.mapID = mapID;
        this.mapObject = mapObject;
        this.version = version;
    }

    /**
     * Getter method for the id of the alias.
     *
     * @return  The id of the alias.
     */
    public Long getId() {
        return id;
    }

    /**
     * Getter method for the name of the alias.
     *
     * @return  The name of the alias.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for the name of the alias.
     *
     * @param name  The new name to set for the alias.
     */
    private void setName(String name) {
        // Hibernate requires the setter for reading values from the database.
        this.name = name;
    }

    /**
     * Getter method for the version of the alias.
     *
     * @return  The version of the alias.
     */
    public int getVersion() {
        return version;
    }

    /**
     * Setter method for the version of the alias.
     *
     * @param version  The new version to set for the alias.
     */
    private void setVersion(int version) {
        // Hibernate requires the setter for reading values from the database.
        this.version = version;
    }

    /**
     * Getter method for the mapID of the alias.
     *
     * @return  The mapID of the alias.
     */
    public Integer getMapID() {
        return mapID;
    }

    /**
     * Setter method for the mapID of the alias.
     *
     * @param mapID  The new mapID to set for the alias.
     */
    private void setMapID(Integer mapID) {
        // Hibernate requires the setter for reading values from the database.
        this.mapID = mapID;
    }

    /**
     * Getter method for the name of the mapID of the alias.
     *
     * @return  The name of the mapID of the alias.
     */
    public String getMapObject() {
        return mapObject;
    }

    /**
     * Setter method for the name of the mapID of the alias.
     *
     * @param mapObject  The new name of the mapID to set for the alias.
     */
    private void setMapObject(String mapObject) {
        // Hibernate requires the setter for reading values from the database.
        this.mapObject = mapObject;
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
     * Two {@link Alias} models are equal, if their id is equal.
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
        Alias alias = (Alias) o;
        return getId().equals(alias.getId());
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
