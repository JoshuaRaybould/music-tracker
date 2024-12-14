package org.example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "album")
public class Album {
    
    @Id @Column(name = "album_id") private int id;

    @Column(name = "album_name") private String name;

    public Album() {}

    public Album(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
