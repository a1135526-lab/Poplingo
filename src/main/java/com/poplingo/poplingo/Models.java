package com.poplingo.poplingo;

import java.util.ArrayList;
import java.util.List;

public class Models {
    public static class Vocabulary {
        public int id; // 🌟 新增：資料庫專屬 id
        public String word, phonetic, translation, lyric;
        public String songTitle;
        public boolean isStarred; // 🌟 新增：收藏狀態

        public Vocabulary(int id, String word, String phonetic, String translation, String lyric, boolean isStarred) {
            this.id = id;
            this.word = word;
            this.phonetic = phonetic;
            this.translation = translation;
            this.lyric = lyric;
            this.isStarred = isStarred;
        }
    }

    public static class Song {
        public int id;
        public String title, audioFileName, fullLyrics;
        public List<Vocabulary> vocabList = new ArrayList<>();

        public Song(int id, String title, String audioFileName, String fullLyrics) {
            this.id = id;
            this.title = title;
            this.audioFileName = audioFileName;
            this.fullLyrics = fullLyrics;
        }
        public void addVocab(Vocabulary v) { vocabList.add(v); }
    }

    public static class Artist {
        public String name;
        public List<Song> songs = new ArrayList<>();
        public Artist(String name) { this.name = name; }
        public void addSong(Song s) { songs.add(s); }
    }

    public static class Country {
        public String name;
        public List<Artist> artists = new ArrayList<>();
        public Country(String name) { this.name = name; }
        public void addArtist(Artist a) { artists.add(a); }
    }
}