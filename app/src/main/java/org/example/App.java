package org.example;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

   User accUser = new User("You");

   private Session session;

   public static void main(String[] args) {
      launch(args);
   }

   @Override
   public void start(Stage primaryStage) {
      DataProcessor dataProcessor = new DataProcessor("songData.json", accUser);
      session = dataProcessor.getSession();

      VBox vbox = new VBox(10); // 10 is the spacing between buttons

      primaryStage.setTitle("Spotify Tracker");

      Button songBtn = new Button();
      songBtn.setText("Top 50 songs");
      songBtn.setOnAction(new EventHandler<ActionEvent>() {

         @Override
         public void handle(ActionEvent event) {
            showSongTable(vbox);
         }
      });

      Button albumBtn = new Button();
      albumBtn.setText("Top 50 albums");
      albumBtn.setOnAction(new EventHandler<ActionEvent>() {

         @Override
         public void handle(ActionEvent event) {
            showAlbumTable(vbox);
         }
      });

      Button artistBtn = new Button();
      artistBtn.setText("Top 50 arists");
      artistBtn.setOnAction(new EventHandler<ActionEvent>() {

         @Override
         public void handle(ActionEvent event) {
            showArtistTable(vbox);
         }
      });

      songBtn.setLayoutX(250);
      songBtn.setLayoutY(220);

      albumBtn.setLayoutX(250);
      albumBtn.setLayoutY(50);

      artistBtn.setLayoutX(250);
      artistBtn.setLayoutY(50);

      vbox.getChildren().add(songBtn);
      vbox.getChildren().add(albumBtn);
      vbox.getChildren().add(artistBtn);
      primaryStage.setScene(new Scene(vbox, 300, 250));
      primaryStage.show();
   }

   public String formatDuration(Duration duration) {
      String hms;
      long hours = duration.toHours();
      int minutes = duration.toMinutesPart();
      int seconds = duration.toSecondsPart();
      if (hours == 0 && minutes == 0) {
         hms = String.format("%d seconds",
               seconds);
      } else if (hours == 0) {
         hms = String.format("%d minutes and %d seconds",
               minutes,
               seconds);
      } else {
         hms = String.format("%d hours %d minutes",
               hours,
               minutes);
      }
      return hms;
   }

   private void showSongTable(VBox root) {
      TableView<Song> table = new TableView<>();
      TableColumn<Song, String> songNameColumn = new TableColumn<>("Song");
      songNameColumn.setCellValueFactory(new PropertyValueFactory<Song, String>("name"));

      TableColumn<Song, String> artistNameColumn = new TableColumn<>("Artist Name");
      artistNameColumn
            .setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getArtist().getName()));

      TableColumn<Song, String> timeListenedColumn = new TableColumn<>("Time Listened");
      timeListenedColumn.setCellValueFactory(cellData -> {
         Duration duration = Duration.ofSeconds(0);
         Set<UserSong> userSongs = cellData.getValue().getUserUserSongs();
         for (UserSong userSong : userSongs) {
            if (userSong.getUser().equals(accUser)) {
               duration = userSong.getTimeListened();
            }
         }
         String formattedDuration = formatDuration(duration);
         return new SimpleStringProperty(formattedDuration);
      });

      table.getColumns().add(songNameColumn);
      table.getColumns().add(artistNameColumn);
      table.getColumns().add(timeListenedColumn);

      table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

      List<Song> songs = session.createNativeQuery("SELECT * FROM songs", Song.class).getResultList();

      for (Song song : songs) {
         table.getItems().add(song);
      }

      root.getChildren().add(table);
   }

   private void showAlbumTable(VBox root) {
      TableView<Album> table = new TableView<>();
      TableColumn<Album, String> albumNameColumn = new TableColumn<>("Album");
      albumNameColumn.setCellValueFactory(new PropertyValueFactory<Album, String>("name"));

      /*
       * TableColumn<Album, String> artistNameColumn = new
       * TableColumn<>("Artist Name");
       * artistNameColumn
       * .setCellValueFactory(cellData -> new
       * SimpleStringProperty(cellData.getValue().getArtist().getName()));
       */

      TableColumn<Album, String> timeListenedColumn = new TableColumn<>("Time Listened");
      timeListenedColumn.setCellValueFactory(cellData -> {
         Duration duration = Duration.ofSeconds(0);
         Set<UserAlbum> userAlbums = cellData.getValue().getUserUserAlbums();
         for (UserAlbum userAlbum : userAlbums) {
            if (userAlbum.getUser().equals(accUser)) {
               duration = userAlbum.getTimeListened();
            }
         }
         String formattedDuration = formatDuration(duration);
         return new SimpleStringProperty(formattedDuration);
      });

      table.getColumns().add(albumNameColumn);
      table.getColumns().add(timeListenedColumn);

      table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

      List<Album> albums = session.createNativeQuery("SELECT * FROM albums", Album.class).getResultList();

      for (Album album : albums) {
         table.getItems().add(album);
      }

      root.getChildren().add(table);
   }

   private void showArtistTable(VBox root) {
      TableView<Artist> table = new TableView<>();
      TableColumn<Artist, String> artistNameColumn = new TableColumn<>("Artist");
      artistNameColumn.setCellValueFactory(new PropertyValueFactory<Artist, String>("name"));

      TableColumn<Artist, String> timeListenedColumn = new TableColumn<>("Time Listened");
      timeListenedColumn.setCellValueFactory(cellData -> {
         Duration duration = Duration.ofSeconds(0);
         Set<UserArtist> userArtists = cellData.getValue().getUserUserArtists();
         for (UserArtist userArtist : userArtists) {
            if (userArtist.getUser().equals(accUser)) {
               duration = userArtist.getTimeListened();
            }
         }
         String formattedDuration = formatDuration(duration);
         return new SimpleStringProperty(formattedDuration);
      });

      table.getColumns().add(artistNameColumn);
      table.getColumns().add(timeListenedColumn);

      table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

      List<Artist> artists = session.createNativeQuery("SELECT * FROM artists", Artist.class).getResultList();

      for (Artist artist : artists) {
         table.getItems().add(artist);
      }

      root.getChildren().add(table);
   }
}
