package de.itermori.pse.kitroomfinder.backend.models;

import java.util.Objects;
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

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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
        BlacklistEntry blacklistEntry = (BlacklistEntry) o;
        return getId().equals(blacklistEntry.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}