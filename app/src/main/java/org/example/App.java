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
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

   User accUser = new User("You");

   public static void main(String[] args) {
      launch(args);
   }

   @Override
   public void start(Stage primaryStage) {
      DataProcessor dataProcessor = new DataProcessor("songData.json", accUser);

      StackPane root = new StackPane();

      primaryStage.setTitle("Spotify Tracker");

      Button btn = new Button();
      btn.setText("Say 'Hello World'");
      btn.setOnAction(new EventHandler<ActionEvent>() {

         @Override
         public void handle(ActionEvent event) {
            System.out.println("Hello World!");

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

            Session session = dataProcessor.getSession();
            List<Song> songs = session.createNativeQuery("SELECT * FROM songs", Song.class).getResultList();

            for (Song song : songs) {
               table.getItems().add(song);
            }

            root.getChildren().add(table);
         }
      });

      root.getChildren().add(btn);
      primaryStage.setScene(new Scene(root, 300, 250));
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
}
