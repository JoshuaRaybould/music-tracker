package org.example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "songs")
public class Song {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "song_id")
   private Integer id;

   @Column(name = "song_name")
   private String name;

   @ManyToOne
   @JoinColumn(name = "artist_id")
   private Artist artist;

   @ManyToOne
   @JoinColumn(name = "album_id")
   private Album album;

   public Song() {
   }

   public Song(String name, Artist artist, Album album) {
      this.name = name;
      this.artist = artist;
      this.album = album;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

}
