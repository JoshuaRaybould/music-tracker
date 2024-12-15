package org.example;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DataProcessor {
   private List<RawSong> allSongs;

   private List<UserSong> userSongs;

   private List<UserArtist> userArtists;

   private List<UserAlbum> userAlbums;

   private HashMap<String, Integer> uriToUserSongPos = new HashMap<>();

   private HashMap<String, Integer> nameToUserArtistPos = new HashMap<>();

   private HashMap<String, Integer> nameToUserAlbumPos = new HashMap<>();

   private SessionFactory sessionFactory;

   private Session session;

   public DataProcessor(String jsonfile) {
      JsonDataLoader dataLoader = new JsonDataLoader(jsonfile);
      allSongs = dataLoader.getSongs();
      setup();
      carryOutProcessing();
   }

   public void setup() {
      // Initialise lists
      userSongs = new ArrayList<>();
      userArtists = new ArrayList<>();
      userAlbums = new ArrayList<>();

      // Set up session factory
      final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

      try {
         sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
      } catch (Exception e) {
         StandardServiceRegistryBuilder.destroy(registry);
         throw new RuntimeException("Failed to initialize Hibernate SessionFactory", e);
      }
   }

   private void carryOutProcessing() {
      session = sessionFactory.openSession();
      User user = new User("Josh");
      // Put the user in the database
      session.beginTransaction();
      session.persist(user);
      session.getTransaction().commit();

      for (RawSong rawSong : allSongs) {

         if (rawSong.getName() == null) {
            continue;
         }
         // For now since only a single person will be using it, there won't be data
         // already in the table, so we'll move forward with that assumption for now

         String artistName = rawSong.getArtistName();
         String albumName = rawSong.getAlbumName();
         String songUri = rawSong.getTrackUri();

         if (!nameToUserArtistPos.containsKey(artistName)) {
            createArtist(user, rawSong, artistName, session);
         } else {
            updateArtist(rawSong, artistName, session);
         }

         if (!nameToUserAlbumPos.containsKey(albumName)) {
            createAlbum(user, rawSong, albumName, session);
         } else {
            updateAlbum(rawSong, albumName, session);
         }

         if (!nameToUserArtistPos.containsKey(songUri)) {
            createSong(user, rawSong, songUri, session);
         } else {
            updateSong(rawSong, songUri, session);
         }

      }
   }

   public Duration toDuration(Integer timeListened) {
      Integer timeL = (timeListened / 1000) / 60;
      return Duration.ofMinutes(timeL);
   }

   private void createArtist(User user, RawSong rawSong, String artistName, Session session) {
      Duration songPlayedDuration = toDuration(rawSong.getTimeListened());
      Artist artist = new Artist(artistName);

      UserArtist userArtist = new UserArtist(user, artist, songPlayedDuration, null, null);
      userArtists.add(userArtist);
      user.addUserArtist(userArtist);
      artist.addUserArtist(userArtist);
      nameToUserArtistPos.put(artistName, userArtists.size() - 1);

      // Put the artist in the database
      session.beginTransaction();
      session.persist(artist);
      session.persist(userArtist);
      session.getTransaction().commit();
   }

   private void updateArtist(RawSong rawSong, String artistName, Session session) {
      Duration songPlayedDuration = toDuration(rawSong.getTimeListened());
      UserArtist userArtist = userArtists.get(nameToUserArtistPos.get(artistName));
      userArtist.updateTimeListened(songPlayedDuration);
      session.beginTransaction();
      session.getTransaction().commit();
   }

   private void createAlbum(User user, RawSong rawSong, String albumName, Session session) {
      Duration songPlayedDuration = toDuration(rawSong.getTimeListened());
      Album album = new Album(albumName);

      UserAlbum userAlbum = new UserAlbum(user, album, songPlayedDuration, null, null);
      userAlbums.add(userAlbum);
      user.addUserAlbum(userAlbum);
      album.addUserAlbum(userAlbum);
      nameToUserAlbumPos.put(albumName, userAlbums.size() - 1);

      // Put the artist in the database
      session.beginTransaction();
      session.persist(album);
      session.persist(userAlbum);
      session.getTransaction().commit();
   }

   private void updateAlbum(RawSong rawSong, String albumName, Session session) {
      Duration songPlayedDuration = toDuration(rawSong.getTimeListened());
      UserAlbum userAlbum = userAlbums.get(nameToUserAlbumPos.get(albumName));
      userAlbum.updateTimeListened(songPlayedDuration);
      session.beginTransaction();
      session.getTransaction().commit();
   }

   private void createSong(User user, RawSong rawSong, String songUri, Session session) {
      Duration songPlayedDuration = toDuration(rawSong.getTimeListened());
      Artist artist = userArtists.get(nameToUserArtistPos.get(rawSong.getArtistName())).getArtist();
      Album album = userAlbums.get(nameToUserAlbumPos.get(rawSong.getAlbumName())).getAlbum();
      Song song = new Song(rawSong.getName(), artist, album);

      UserSong userSong = new UserSong(user, song, songPlayedDuration, null, null, null);
      userSongs.add(userSong);
      user.addUserSong(userSong);
      song.addUserSong(userSong);

      uriToUserSongPos.put(songUri, userSongs.size() - 1);

      // Put the artist in the database
      session.beginTransaction();
      session.merge(user);
      session.persist(artist);
      session.persist(userSong);
      session.getTransaction().commit();
   }

   private void updateSong(RawSong rawSong, String songUri, Session session) {
      Duration songPlayedDuration = toDuration(rawSong.getTimeListened());
      UserSong userSong = userSongs.get(uriToUserSongPos.get(songUri));
      userSong.updateTimeListened(songPlayedDuration);
      session.beginTransaction();
      session.merge(userSong);
      session.getTransaction().commit();
   }

   public Session getSession() {
      return session;
   }
}
