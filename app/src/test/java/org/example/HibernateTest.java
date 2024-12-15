package org.example;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.BeforeEach;

public class HibernateTest {

   private SessionFactory sessionFactory;

   @BeforeEach
   protected void setUp() throws Exception {
      final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

      try {
         sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
      } catch (Exception e) {
         StandardServiceRegistryBuilder.destroy(registry);
         throw new RuntimeException("Failed to initialize Hibernate SessionFactory", e);
      }
   }

   @Test
   void testUserCreation() {

      User user = new User("Josh");
      Session session = sessionFactory.openSession();
      session.beginTransaction();

      session.persist(user);
      session.getTransaction().commit();
      session.close();
   }

   @Test
   void testManyToMany() {
      User user = new User("2nd guy");
      Artist artist = new Artist("Kendrick Lamar");
      Duration totalDuration = Duration.ofDays(60).plus(Duration.ofMinutes(60));
      UserArtist userArtist = new UserArtist(user, artist, totalDuration, LocalDate.of(2020, 1, 8),
            LocalDate.of(2020, 1, 8));
      user.addUserArtist(userArtist);
      artist.addUserArtist(userArtist);
      Session session = sessionFactory.openSession();
      session.beginTransaction();

      session.persist(user);
      session.persist(artist);
      session.persist(userArtist);
      session.getTransaction().commit();

      // Begin a transaction
      Transaction transaction = session.beginTransaction();

      // Retrieve the object using the primary key
      User usr = (User) session.get(User.class, 1L);

      // Commit the transaction
      transaction.commit();

      Set<UserArtist> userArtists = usr.getUserUserArtists();
      List<UserArtist> userArts = new ArrayList<>(userArtists);
      UserArtist userArt = userArts.get(0);
      Artist theArtist = userArt.getArtist();

      assertEquals("Kendrick Lamar", theArtist.getName());
      assertEquals(totalDuration, userArt.getTimeListened());

      session.close();
   }

}
