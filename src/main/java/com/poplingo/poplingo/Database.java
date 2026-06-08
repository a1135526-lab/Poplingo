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
                            rs.getInt("id"), // 🌟 讀取 id
                            rs.getString("word"), rs.getString("phonetic"),
                            rs.getString("translation"), rs.getString("lyric_context"),
                            rs.getInt("is_starred") == 1 // 🌟 讀取收藏狀態
                    );
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    // 🌟 更新：複習模式只撈取該國家「已加上星號 (is_starred = 1)」的單字
    public static List<Models.Vocabulary> getVocabsByCountry(int countryId) {
        List<Models.Vocabulary> list = new ArrayList<>();
        String sql = "SELECT v.id, v.word, v.phonetic, v.translation, v.lyric_context, s.title, v.is_starred " +
                "FROM vocabularies v " +
                "JOIN songs s ON v.song_id = s.id " +
                "JOIN artists a ON s.artist_id = a.id " +
                "WHERE a.country_id = ? AND v.is_starred = 1"; // 🌟 限制必須已收藏

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, countryId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Models.Vocabulary vocab = new Models.Vocabulary(
                            rs.getInt("id"),
                            rs.getString("word"),
                            rs.getString("phonetic"),
                            rs.getString("translation"),
                            rs.getString("lyric_context"),
                            rs.getInt("is_starred") == 1
                    );
                    vocab.songTitle = rs.getString("title");
                    list.add(vocab);
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    // 🌟 新增：即時更新單字的星號收藏狀態
    public static void updateStarStatus(int vocabId, boolean isStarred) {
        String sql = "UPDATE vocabularies SET is_starred = ? WHERE id = ?";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, isStarred ? 1 : 0);
            stmt.setInt(2, vocabId);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
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