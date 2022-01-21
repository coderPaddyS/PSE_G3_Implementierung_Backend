package de.itermori.pse.kitroomfinder.backend.models;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.security.core.GrantedAuthority;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ElementCollection(fetch = FetchType.LAZY)
    private Set<GrantedAuthority> authorities = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aliasSuggestion_id")
    private AliasSuggestion aliasSuggestion;

    // Hibernate requires an explicitly written standard constructor.
    public User() {
    }

    public User(String name, Set<GrantedAuthority> authorities) {
        this.name = name;
        this.authorities = authorities;
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

    public Set<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "-" + this.getId();
    }
    
}
