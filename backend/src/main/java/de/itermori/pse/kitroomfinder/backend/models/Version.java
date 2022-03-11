package de.itermori.pse.kitroomfinder.backend.models;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Provides a model for the entity Version.
 * Represents an alias for a specific {@link Version}.
 *
 * @author Lukas Zetto
 * @author Adriano Castro
 * @version 1.0
 */
@Entity
public class Version {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "currentVersion", nullable = false)
    private Integer currentVersion;

    /**
     * The standard constructor of the model.
     */
    public Version() {
        // Hibernate requires an explicitly written standard constructor.
    }

    /**
     * The constructor for the initialization of an alias entity.
     *
     * @param currentVersion   The current version of the database.
     */
    public Version(Integer currentVersion) {
        this.currentVersion = currentVersion;
    }

    /**
     * Getter method for the id of the version entity.
     *
     * @return  The id of the version entity.
     */
    public Long getId() {
        return id;
    }

    /**
     * Getter method for the current version of the database.
     *
     * @return  The current version of the database.
     */
    public Integer getCurrentVersion() {
        return currentVersion;
    }

    /**
     * Setter method for the current version of the database.
     *
     * @param currentVersion  The new current version to set for the version entity (and database).
     */
    public void setCurrentVersion(Integer currentVersion) {
        // Hibernate requires the setter for reading values from the database.
        this.currentVersion = currentVersion;
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
     * Two {@link Version} models are equal, if their id is equal.
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
        Version version = (Version) o;
        return getId().equals(version.getId());
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
