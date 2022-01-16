package de.itermori.pse.kitroomfinder.backend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MapID {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    // ID that is stored in the database in the frontend
    @Column(name = "app_id", nullable = false)
    private Integer appID;

    // Hibernate requires an explicit written standard constructor.
    public MapID() {
    }

    public MapID(String name, Integer appID) {
        this.name = name;
        this.appID = appID;
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

    public Integer getAppID() {
        return appID;
    }

    // Hibernate requires the setter for reading values from the database.
    private void setAppID(Integer appID) {
        this.appID = appID;
    }

    @Override
    public String toString() {
        return "MapID{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", appID=" + appID +
                '}';
    }

}
