CREATE TABLE users (
  user_id INTEGER GENERATED ALWAYS AS IDENTITY, 
  user_name VARCHAR(255),
  PRIMARY KEY(user_id)
); 

CREATE TABLE artists (
  artist_id INTEGER GENERATED ALWAYS AS IDENTITY,
  artist_name VARCHAR(255),
  PRIMARY KEY(artist_id)
);

CREATE TABLE users_artists (
  user_id INTEGER REFERENCES users(user_id),
  artist_id INTEGER REFERENCES artists(artist_id),
  time_listened_artist INTERVAL,
  first_listened_artist INTERVAL,
  last_listened_artist INTERVAL,
  PRIMARY KEY (user_id, artist_id)
);

CREATE TABLE songs (
  song_id INTEGER GENERATED ALWAYS AS IDENTITY,
  song_name VARCHAR(255),
  PRIMARY KEY(song_id)
);

CREATE TABLE users_songs (
  user_id INTEGER REFERENCES users(user_id),
  song_id INTEGER REFERENCES songs(song_id),
  time_listened_song INTERVAL,
  first_listened_song TIMESTAMP,
  last_listened_song TIMESTAMP,
  longest_streak INTEGER,
  PRIMARY KEY(user_id, song_id)
);

CREATE TABLE artists_songs (
  artist_id INTEGER REFERENCES artists(artist_id),
  song_id INTEGER REFERENCES songs(song_id),
  PRIMARY KEY(artist_id, song_id)
);

CREATE TABLE albums (
  album_id INTEGER GENERATED ALWAYS AS IDENTITY,
  album_name VARCHAR(255),
  album_length INTERVAL,
  first_listened_album TIMESTAMP,
  PRIMARY KEY(album_id)
);

CREATE TABLE users_albums (
  user_id INTEGER REFERENCES users(user_id),
  album_id INTEGER REFERENCES albums(album_id),
  PRIMARY KEY(user_id, album_id)
);

CREATE TABLE artists_albums (
  artist_id INTEGER REFERENCES artists(artist_id),
  album_id INTEGER REFERENCES albums(album_id),
  PRIMARY KEY(artist_id, album_id)
);
