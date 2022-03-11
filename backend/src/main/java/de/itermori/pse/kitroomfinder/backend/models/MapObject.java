package de.itermori.pse.kitroomfinder.backend.models;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Provides a model for the entity MapObject.
 * Represents a map object in the map of the KIT Campus.
 *
 * @author Lukas Zetto
 * @author Adriano Castro
 * @version 1.0
 */
@Entity
public class MapObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "mapID", nullable = false)
    private Integer mapID;

    /**
     * The standard constructor of the model.
     */
    public MapObject() {
        // Hibernate requires an explicitly written standard constructor.
    }

    /**
     * The constructor for the initialization of a mapID entity.
     *
     * @param name      The name of the map object entity.
     * @param mapID     The id of the map object in the KIT Campus.
     */
    public MapObject(String name, Integer mapID) {
        this.name = name;
        this.mapID = mapID;
    }

    /**
     * Getter method for the id of the map object entity.
     *
     * @return  The id of the map object entity.
     */
    public Long getId() {
        return id;
    }

    /**
     * Getter method for the name of the map object entity.
     *
     * @return  The name of the mapID entity.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for the name of the map object entity.
     *
     * @param name  The new name to set for the map object entity.
     */
    public void setName(String name) {
        // Hibernate requires the setter for reading values from the database.
        this.name = name;
    }

    /**
     * Getter method for the mapID of the map object entity.
     *
     * @return  The mapID of the map object entity.
     */
    public Integer getMapID() {
        return mapID;
    }

    /**
     * Setter method for the mapID of the map object entity.
     *
     * @param mapID  The new mapID to set for the map object entity.
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
     * Two {@link MapObject} models are equal, if their id is equal.
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
        MapObject mapID = (MapObject) o;
        return getId().equals(mapID.getId());
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
