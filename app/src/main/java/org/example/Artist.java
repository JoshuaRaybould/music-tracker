package org.example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "artists")
public class Artist {
    
    @Id @Column(name = "artist_id") private int id;
    
    @Column(name = "artist_name") private String name;

    public Artist(){}

    public Artist(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
