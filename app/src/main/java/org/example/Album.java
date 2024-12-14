package org.example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "albums")
public class Album {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "album_id")
   private Integer id;

   @Column(name = "album_name")
   private String name;

   public Album() {
   }

   public Album(String name) {
      this.name = name;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public Integer getId() {
      return id;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

}
