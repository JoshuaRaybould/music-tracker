package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Song {
    
    @JsonProperty("master_metadata_track_name")
    private String name;

    @JsonProperty("ms_played")
    private int timeListened;

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
    
    public String getTrackUri() {
        return trackUri;
    }
}
