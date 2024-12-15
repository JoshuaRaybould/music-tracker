package org.example;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
   @JoinColumn(name = "album_id")
   private Album album;

   @OneToMany(mappedBy = "song")
   private Set<UserSong> userSongs = new HashSet<UserSong>();

   public Song() {
   }

   public Song(String name, Album album) {
      this.name = name;
      this.album = album;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
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

   public Album getAlbum() {
      return album;
   }

}
