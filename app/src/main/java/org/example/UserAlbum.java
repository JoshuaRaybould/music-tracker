package org.example;

import java.time.Duration;
import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users_albums")
public class UserAlbum {

   @Id
   @GeneratedValue
   private Integer id;

   @ManyToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "user_id")
   private User user;

   @ManyToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "album_id")
   private Album album;

   @Column(name = "time_listened", columnDefinition = "interval")
   private Duration timeListened;

   @Column(name = "first_listened")
   private LocalDate firstListenedDate;

   @Column(name = "last_listened")
   private LocalDate lastListenedDate;

   public UserAlbum() {
   }

   public UserAlbum(User user, Album album, Duration timeListened, LocalDate firstListenedDate,
         LocalDate lastListenedDate) {
      this.user = user;
      this.album = album;
      this.timeListened = timeListened;
      this.firstListenedDate = firstListenedDate;
      this.lastListenedDate = lastListenedDate;
   }

   public void setUser(User user) {
      this.user = user;
   }

   public User getUser() {
      return user;
   }

   public void setAlbum(Album album) {
      this.album = album;
   }

   public Album getAlbum() {
      return album;
   }

   public void setTimeListened(Duration timeListened) {
      this.timeListened = timeListened;
   }

   public Duration getTimeListened() {
      return timeListened;
   }

   public void setFirstListenedDate(LocalDate firstListenedDate) {
      this.firstListenedDate = firstListenedDate;
   }

   public LocalDate getFirstListenedDate() {
      return firstListenedDate;
   }

   public void setLastListenedDate(LocalDate lastListenedDate) {
      this.lastListenedDate = lastListenedDate;
   }

   public LocalDate getLastListenedDate() {
      return lastListenedDate;
   }
}