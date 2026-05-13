package com.poplingo.poplingo;

import java.util.ArrayList;
import java.util.List;

public class Models {
    public static class Vocabulary {
        String word, phonetic, translation, lyric;
        public Vocabulary(String word, String phonetic, String translation, String lyric) {
            this.word = word; this.phonetic = phonetic;
            this.translation = translation; this.lyric = lyric;
        }
    }

    public static class Song {
        int id; // 🌟 新增：對應資料庫的 id
        String title, audioFileName, fullLyrics;
        List<Vocabulary> vocabList = new ArrayList<>();

        public Song(int id, String title, String audioFileName, String fullLyrics) {
            this.id = id;
            this.title = title;
            this.audioFileName = audioFileName;
            this.fullLyrics = fullLyrics;
        }
        public void addVocab(Vocabulary v) { vocabList.add(v); }
    }

    public static class Artist {
        String name;
        List<Song> songs = new ArrayList<>();
        public Artist(String name) { this.name = name; }
        public void addSong(Song s) { songs.add(s); }
    }

    public static class Country {
        String name;
        List<Artist> artists = new ArrayList<>();
        public Country(String name) { this.name = name; }
        public void addArtist(Artist a) { artists.add(a); }
    }
}