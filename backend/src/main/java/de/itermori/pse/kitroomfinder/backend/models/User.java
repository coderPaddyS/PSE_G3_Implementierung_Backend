package de.itermori.pse.kitroomfinder.backend.models;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Provides a model for the entity User.
 * Represents a user of the application.
 *
 * @author Lukas Zetto
 * @author Adriano Castro
 * @version 1.0
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column
    private String authorities;

    /**
     * The standard constructor of the model.
     */
    public User() {
        // Hibernate requires an explicitly written standard constructor.
    }

    /**
     * The constructor for the initialization of a user entity.
     *
     * @param name          The name of the user.
     * @param authorities   The authorities of the user.
     */
    public User(String name, String authorities) {
        this.name = name;
        this.authorities = authorities;
    }

    /**
     * Getter method for the id of the user.
     *
     * @return  The id of the user.
     */
    public Long getId() {
        return id;
    }

    /**
     * Getter method for the name of the user.
     *
     * @return  The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for the name of the user.
     *
     * @param name  The new name to set for the user.
     */
    public void setName(String name) {
        // Hibernate requires the setter for reading values from the database.
        this.name = name;
    }

    /**
     * Getter method for the authorities of the user.
     *
     * @return  The authorities of the user.
     */
    public String getAuthorities() {
        return authorities;
    }

    /**
     * Setter method for the authorities of the user.
     *
     * @param authorities  The new authorities to set for the user.
     */
    public void setAuthorities(String authorities) {
        // Hibernate requires the setter for reading values from the database.
        this.authorities = authorities;
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
     * Two {@link User} models are equal, if their id is equal.
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
        User user = (User) o;
        return getId().equals(user.getId());
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
