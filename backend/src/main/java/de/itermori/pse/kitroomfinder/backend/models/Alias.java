package de.itermori.pse.kitroomfinder.backend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Alias {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "mapID", nullable = false)
    private Integer mapID;

    // Hibernate requires an explicitly written standard constructor.
    public Alias() {
    }

    public Alias(String name, Integer mapID) {
        this.name = name;
        this.mapID = mapID;
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

    @Override
    public String toString() {
        return "Alias{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mapID=" + mapID +
                '}';
    }
    
}
