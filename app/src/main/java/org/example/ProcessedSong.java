package org.example;

public class ProcessedSong {
    
    private String name;

    private String uri;

    private int minutesListened;

    private int artistId;

    private int albumId;

    public ProcessedSong(){

    }

    public ProcessedSong(String name, String uri, int timeListened, int artistId, int albumId){
        this.name = name;
        this.uri = uri;
        this.minutesListened = (timeListened/1000)/60;
        this.artistId = artistId;
        this.albumId = albumId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public void setMinutesListened(int minutesListened) {
        this.minutesListened = minutesListened;
    }

    public int getMinutesListened() {
        return minutesListened;
    }

    public void addTimeListened(int timeListened) {
        minutesListened += (timeListened/1000)/60;
    }


}
