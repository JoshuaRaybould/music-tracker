package org.example;

import java.time.Duration;

import org.hibernate.annotations.NaturalId;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users_albums")
public class UserAlbum {

   @Id
   @GeneratedValue
   private Integer id;

   @NaturalId
   @Column(name = "user_id")
   private User user;

   @NaturalId
   @Column(name = "album_id")
   private Album album;

   @Column(name = "time_listened", columnDefinition = "interval")
   private Duration timeListened;

   @Column(name = "first_listened")
   private java.util.Date firstListenedDate;

   @Column(name = "last_listened")
   private java.util.Date lastListenedDate;

   public UserAlbum() {
   }

   public UserAlbum(User user, Album album, Duration timeListened, java.util.Date firstListenedDate,
         java.util.Date lastListenedDate) {
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

   public void setFirstListenedDate(java.util.Date firstListenedDate) {
      this.firstListenedDate = firstListenedDate;
   }

   public java.util.Date getFirstListenedDate() {
      return firstListenedDate;
   }

   public void setLastListenedDate(java.util.Date lastListenedDate) {
      this.lastListenedDate = lastListenedDate;
   }

   public java.util.Date getLastListenedDate() {
      return lastListenedDate;
   }
}