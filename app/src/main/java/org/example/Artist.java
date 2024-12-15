package org.example;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "artists")
public class Artist {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "artist_id")
   private Integer id;

   @Column(name = "artist_name")
   private String name;

   @OneToMany(mappedBy = "artist")
   private Set<UserArtist> userArtists = new HashSet<UserArtist>();

   public Artist() {
   }

   public Artist(String name) {
      this.name = name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

   public Set<UserArtist> getUserUserArtists() {
      return userArtists;
   }

   public void setUserArtists(Set<UserArtist> userArtists) {
      this.userArtists = userArtists;
   }

   public void addUserArtist(UserArtist userArtist) {
      userArtists.add(userArtist);
   }
}
