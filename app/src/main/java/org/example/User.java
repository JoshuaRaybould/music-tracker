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
@Table(name = "users")
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "user_id")
   private Integer id;

   @Column(name = "user_name")
   private String name;

   @OneToMany(mappedBy = "user")
   private Set<UserArtist> userArtists = new HashSet<UserArtist>();

   @OneToMany(mappedBy = "user")
   private Set<UserAlbum> userAlbums = new HashSet<UserAlbum>();

   @OneToMany(mappedBy = "user")
   private Set<UserSong> userSongs = new HashSet<UserSong>();

   public User() {
   }

   public User(String name) {
      this.name = name;
   }

   public void setId(int id) {
      this.id = id;
   }

   public Integer getId() {
      return id;
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

   public Set<UserAlbum> getUserUserAlbums() {
      return userAlbums;
   }

   public void setUserAlbums(Set<UserAlbum> userAlbums) {
      this.userAlbums = userAlbums;
   }

   public void addUserAlbum(UserAlbum userAlbum) {
      userAlbums.add(userAlbum);
   }

   public Set<UserSong> getUserUserSongs() {
      return userSongs;
   }

   public void setUserSongs(Set<UserSong> userSongs) {
      this.userSongs = userSongs;
   }

   public void addUserSong(UserSong userSong) {
      userSongs.add(userSong);
   }
}
