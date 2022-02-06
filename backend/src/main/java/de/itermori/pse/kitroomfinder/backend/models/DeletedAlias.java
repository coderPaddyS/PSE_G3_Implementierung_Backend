package de.itermori.pse.kitroomfinder.backend.models;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    // Hibernate requires an explicitly written standard constructor.
    public DeletedAlias() {
    }

    public DeletedAlias(String name, Integer mapID, int version) {
        this.name = name;
        this.mapID = mapID;
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getVersion() {return version; }

    // Hibernate requires the setter for reading values from the database.
    private void setVersion(int version) {
        this.version = version;
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
        DeletedAlias deletedAlias = (DeletedAlias) o;
        return getId().equals(deletedAlias.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
