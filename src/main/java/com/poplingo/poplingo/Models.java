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
        String title;
        String audioFileName; // 音樂檔名
        String fullLyrics;    // 完整歌詞
        List<Vocabulary> vocabList = new ArrayList<>();

        public Song(String title, String audioFileName, String fullLyrics) {
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