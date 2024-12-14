package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Song {
    
    @JsonProperty("master_metadata_track_name")
    private String name;

    @JsonProperty("ms_played")
    private int timeListened;

    @JsonProperty("master_metadata_album_artist_name")
    private String artistName;
    
    @JsonProperty("master_metadata_album_album_name")
    private String albumName;

    @JsonProperty("spotify_track_uri")
    private String trackUri;

    public Song(){

    }

    public String getName() {
        return name;
    }

    public int getTimeListened() {
        return timeListened;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getAlbumName() {
        return albumName;
    }
    
    public String getTrackUri() {
        return trackUri;
    }
}
