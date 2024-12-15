package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.hibernate.Session;

public class LogicTest {

   @Test
   void testUserCreation() {
      User user = new User("J");
      Boolean foundKendrick = false;

      DataProcessor processData = new DataProcessor("exampleData.json", user);
      Session session = processData.getSession();
      session.beginTransaction();

      User usr = (User) session.get(User.class, 1L);
      session.getTransaction().commit();

      Set<UserArtist> userArtists = usr.getUserUserArtists();
      List<UserArtist> userArts = new ArrayList<>(userArtists);
      for (UserArtist userArt : userArts) {
         Artist theArtist = userArt.getArtist();
         if (theArtist.getName().equals("Kendrick Lamar")) {
            foundKendrick = true;
            break;
         }
      }
      assertTrue(foundKendrick);
      session.close();
   }

}
