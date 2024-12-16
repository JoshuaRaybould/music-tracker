package org.example;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDataLoader {

   private List<RawSong> songs;

   public JsonDataLoader(String jsonfile) {
      songs = new ArrayList<RawSong>();
      if (jsonfile.equals("AllSongData")) {
         loadAllData();
      } else {
         loadTestData(jsonfile);
      }
   }

   public void loadTestData(String jsonFile) {
      InputStream inStream = getClass().getClassLoader().getResourceAsStream(jsonFile);
      ObjectMapper objectMapper = new ObjectMapper();
      try {
         songs = objectMapper.readValue(inStream, new TypeReference<List<RawSong>>() {
         });
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public void loadAllData() {
      List<RawSong> curSongs = new ArrayList<>();
      String path = "../AllSongData";
      File files = new File(path);
      File[] dir = files.listFiles();

      for (File file : dir) {
         ObjectMapper objectMapper = new ObjectMapper();
         String fileInfo;
         try {
            fileInfo = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            curSongs = objectMapper.readValue(fileInfo, new TypeReference<List<RawSong>>() {
            });

            songs = Stream.concat(songs.stream(), curSongs.stream()).toList();

         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }

   public List<RawSong> getSongs() {
      return songs;
   }
}
