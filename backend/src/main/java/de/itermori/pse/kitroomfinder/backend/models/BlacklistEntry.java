package de.itermori.pse.kitroomfinder.backend.models;

import javax.persistence.*;

@Entity
public class BlacklistEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    // Hibernate requires an explicitly written standard constructor.
    public BlacklistEntry() {
    }

    public BlacklistEntry(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}