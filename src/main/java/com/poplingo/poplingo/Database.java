package com.poplingo.poplingo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    public static List<Models.Country> getAllCountries() {
        List<Models.Country> countries = new ArrayList<>();
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM countries");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int countryId = rs.getInt("id");
                Models.Country country = new Models.Country(rs.getString("name"));
                loadArtistsForCountry(conn, countryId, country);
                countries.add(country);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return countries;
    }

    // 🌟 新增：即時查詢單字功能
    public static Models.Vocabulary searchWord(int songId, String searchWord) {
        String sql = "SELECT * FROM vocabularies WHERE song_id = ? AND (word = ? OR ? LIKE CONCAT('%', word, '%')) LIMIT 1";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, songId);
            stmt.setString(2, searchWord);
            stmt.setString(3, searchWord);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Models.Vocabulary(
                            rs.getString("word"), rs.getString("phonetic"),
                            rs.getString("translation"), rs.getString("lyric_context")
                    );
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    private static void loadArtistsForCountry(Connection conn, int countryId, Models.Country country) throws SQLException {
        String sql = "SELECT * FROM artists WHERE country_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, countryId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int artistId = rs.getInt("id");
                    Models.Artist artist = new Models.Artist(rs.getString("name"));
                    loadSongsForArtist(conn, artistId, artist);
                    country.addArtist(artist);
                }
            }
        }
    }

    private static void loadSongsForArtist(Connection conn, int artistId, Models.Artist artist) throws SQLException {
        String sql = "SELECT * FROM songs WHERE artist_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, artistId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // 🌟 這裡要記得讀取 id 並傳給 Song 建構子
                    Models.Song song = new Models.Song(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("audio_file_name"),
                            rs.getString("full_lyrics")
                    );
                    artist.addSong(song);
                }
            }
        }
    }
}