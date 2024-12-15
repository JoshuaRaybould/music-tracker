package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {

   public static void main(String[] args) {
      while (true) {
         BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

         System.out.println("Give command");
         String command;
         try {
            command = reader.readLine();
            if (command.equals("l")) {
               JsonDataLoader loader = new JsonDataLoader("songData.json");

            } else if (command.equals("p")) {
               ProcessData processData = new ProcessData("songData.json");
            }
         } catch (IOException e) {
            e.printStackTrace();
         }

      }
   }
}
