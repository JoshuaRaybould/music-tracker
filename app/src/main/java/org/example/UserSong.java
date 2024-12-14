package org.example;

import java.time.Duration;

import org.hibernate.annotations.NaturalId;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users_songs")
public class UserSong {

   @Id
   @GeneratedValue
   private Integer id;

   @ManyToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "user_id")
   private User user;

   @ManyToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "song_id")
   private Song song;

   @Column(name = "time_listened", columnDefinition = "interval")
   private Duration timeListened;

   @Column(name = "first_listened")
   private java.util.Date firstListenedDate;

   @Column(name = "last_listened")
   private java.util.Date lastListenedDate;

   @Column(name = "longest_streak")
   private Integer longestStreak;

   public UserSong() {
   }

   public UserSong(User user, Song song, Duration timeListened, java.util.Date firstListenedDate,
         java.util.Date lastListenedDate, Integer longestStreak) {
      this.user = user;
      this.song = song;
      this.timeListened = timeListened;
      this.firstListenedDate = firstListenedDate;
      this.lastListenedDate = lastListenedDate;
      this.longestStreak = longestStreak;
   }

   public void setUser(User user) {
      this.user = user;
   }

   public User getUser() {
      return user;
   }

   public void setSong(Song song) {
      this.song = song;
   }

   public Song getSong() {
      return song;
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

   public void setLongestStreak(Integer longestStreak) {
      this.longestStreak = longestStreak;
   }

   public Integer getLongestStreak() {
      return longestStreak;
   }
}
