package org.example;

import java.util.HashMap;
import java.util.List;

public class ProcessData {
    private List<Song> allSongs;

    private List<ProcessedSong> processedSongs;

    private List<Artist> artists;

    private List<Album> albums;

    private HashMap<String, Integer> uriToSongPos = new HashMap<>();

    private HashMap<String, Integer> nameToArtistPos = new HashMap<>();

    private HashMap<String, Integer> nameToAlbumPos = new HashMap<>();

    public ProcessData(){
        JsonDataLoader dataLoader = new JsonDataLoader();
        allSongs = dataLoader.getSongs();

        carryOutProcessing();

    }

    private void carryOutProcessing() {

        for (Song song : allSongs) {
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
                addProcessedSong(song, artistId, albumId);
            } else {
                updateProcessedSong(song);
            }

        } 
    }

    private int createArtist(Song song) {
        return 0;
    }

    private int updateArtist(Song song) {
        return 0;
    }

    private int createAlbum(Song song) {
        return 0;
    }

    private int updateAlbum(Song song) {
        return 0;
    }

    private void addProcessedSong(Song song, int artistId, int albumId) {
        ProcessedSong processedSong = new ProcessedSong(song.getName(), song.getTrackUri(), song.getTimeListened(), artistId, albumId);
        processedSongs.add(processedSong);
        uriToSongPos.put(song.getTrackUri(), processedSongs.size()-1);
    }

    private void updateProcessedSong(Song song) {
        int curSongPos = uriToSongPos.get(song.getTrackUri());
        ProcessedSong curSong = processedSongs.get(curSongPos);
        curSong.addTimeListened(song.getTimeListened());
    }
}
