package de.itermori.pse.kitroomfinder.backend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Version {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "currentVersion", nullable = false)
    private Integer currentVersion;

    // Hibernate requires an explicitly written standard constructor.
    public Version() {
    }

    public Version(Integer currentVersion) {
        this.currentVersion = currentVersion;
    }

    public Long getId() {
        return id;
    }

    public Integer getCurrentVersion() {
        return currentVersion;
    }

    // Hibernate requires the setter for reading values from the database.
    private void setCurrentVersion(Integer currentVersion) {
        this.currentVersion = currentVersion;
    }
    
}
