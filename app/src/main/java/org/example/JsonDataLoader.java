package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDataLoader {

   private List<RawSong> songs;

   public JsonDataLoader(String jsonfile) {
      InputStream inStream = getClass().getClassLoader().getResourceAsStream(jsonfile);
      ObjectMapper objectMapper = new ObjectMapper();
      try {
         songs = objectMapper.readValue(inStream, new TypeReference<List<RawSong>>() {
         });
         for (RawSong song : songs) {
            System.out.println(song.getName());
         }
      } catch (IOException e) {
         e.printStackTrace();
      }

   }

   public List<RawSong> getSongs() {
      return songs;
   }
}
