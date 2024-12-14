package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDataLoader {
    
    private List<Song> songs;

    public JsonDataLoader(){
        InputStream inStream = getClass().getClassLoader().getResourceAsStream("songData.json");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            songs = objectMapper.readValue(inStream, new TypeReference<List<Song>>() {});
            for (Song song : songs) {
                System.out.println(song.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<Song> getSongs() {
        return songs;
    }
}
