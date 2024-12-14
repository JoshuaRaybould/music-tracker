package org.example;

import java.util.HashMap;
import java.util.List;

public class ProcessData {
   private List<RawSong> allSongs;

   private List<Song> processedSongs;

   private List<Artist> artists;

   private List<Album> albums;

   private HashMap<String, Integer> uriToSongPos = new HashMap<>();

   private HashMap<String, Integer> nameToArtistPos = new HashMap<>();

   private HashMap<String, Integer> nameToAlbumPos = new HashMap<>();

   public ProcessData() {
      JsonDataLoader dataLoader = new JsonDataLoader();
      allSongs = dataLoader.getSongs();

      carryOutProcessing();

   }

   private void carryOutProcessing() {

      for (RawSong song : allSongs) {
         int artistId;
         int albumId;

         if (!nameToArtistPos.containsKey(song.getArtistName())) {
            artistId = createArtist(song);
         } else {
            artistId = updateArtist(song);
         }

         if (!nameToAlbumPos.containsKey(song.getAlbumName())) {
            albumId = createAlbum(song);
         } else {
            albumId = updateAlbum(song);
         }

         if (!uriToSongPos.containsKey(song.getTrackUri())) {
            // addProcessedSong(song, artistId, albumId);
         } else {
            // updateProcessedSong(song);
         }

      }
   }

   private int createArtist(RawSong song) {
      Artist artist = new Artist();
      return 0;
   }

   private int updateArtist(RawSong song) {
      return 0;
   }

   private int createAlbum(RawSong song) {
      return 0;
   }

   private int updateAlbum(RawSong song) {
      return 0;
   }
}
