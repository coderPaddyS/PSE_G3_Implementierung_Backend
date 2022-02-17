package de.itermori.pse.kitroomfinder.backend.models;

import java.util.Objects;
import javax.persistence.*;

/**
 * Provides a model for the entity {@link BlacklistEntry}.
 *
 * @author Lukas Zetto
 * @author Adriano Castro
 * @version 1.0
 */
@Entity
public class BlacklistEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    /**
     * The standard constructor of the model.
     */
    public BlacklistEntry() {
        // Hibernate requires an explicitly written standard constructor.
    }

    /**
     * The constructor for the initialization of a blacklist entry entity.
     *
     * @param name      The name of the blacklist entry.
     */
    public BlacklistEntry(String name) {
        this.name = name;
    }

    /**
     * Getter method for the id of the blacklist entry.
     *
     * @return  The id of the blacklist entry.
     */
    public Long getId() {
        return id;
    }

    /**
     * Getter method for the name of the blacklist entry.
     *
     * @return  The name of the blacklist entry.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for the name of the blacklist entry.
     *
     * @param name  The new name to set for the blacklist entry.
     */
    private void setName(String name) {
        // Hibernate requires the setter for reading values from the database.
        this.name = name;
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
     * Two {@link BlacklistEntry} models are equal, if their id is equal.
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
        BlacklistEntry blacklistEntry = (BlacklistEntry) o;
        return getId().equals(blacklistEntry.getId());
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