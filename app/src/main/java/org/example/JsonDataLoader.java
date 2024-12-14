package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDataLoader {
    
    public JsonDataLoader(){
        InputStream inStream = getClass().getClassLoader().getResourceAsStream("songData.json");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Song> songs = objectMapper.readValue(inStream, new TypeReference<List<Song>>() {});
            for (Song song : songs) {
                System.out.println(song.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
