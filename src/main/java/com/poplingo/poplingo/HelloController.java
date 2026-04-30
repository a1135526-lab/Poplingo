package com.poplingo.poplingo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URL;
import javafx.scene.control.Slider;
import javafx.util.Duration;

public class HelloController {

    @FXML private VBox pageLogin, pageSelection, pageLearn, listContainer;
    @FXML private Label selectionTitle, learnTitle, wordLabel, phoneticLabel, translationLabel, lyricLabel, fullLyricsLabel;
    @FXML private Button playButton;
    @FXML private Slider progressBar;
    @FXML private Label currentTimeLabel;
    @FXML private Label totalTimeLabel;

    private Models.Song currentSong;
    private int currentVocabIndex = 0;
    private String currentView = "LOGIN";
    private MediaPlayer mediaPlayer;
    private boolean isPlaying = false;

    // --- 畫面切換邏輯 ---
    private void showPage(String pageName) {
        pageLogin.setVisible(pageName.equals("LOGIN"));
        pageSelection.setVisible(pageName.equals("SELECT"));
        pageLearn.setVisible(pageName.equals("LEARN"));
    }

    @FXML
    protected void onLoginClick() {
        loadCountries();
    }

    @FXML
    protected void onBackClick() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            isPlaying = false;
        }

        if (currentView.equals("ARTIST")) loadCountries();
        else if (currentView.equals("SONG")) loadCountries();
        else if (currentView.equals("LEARN")) loadCountries();
        else if (currentView.equals("SELECT")) showPage("LOGIN");
    }

    // --- 選單載入邏輯 ---
    private void loadCountries() {
        showPage("SELECT");
        currentView = "SELECT";
        selectionTitle.setText("選擇想探索的國家");
        listContainer.getChildren().clear();

        for (Models.Country c : Database.getAllCountries()) {
            Button btn = createListButton(c.name);
            btn.setOnAction(e -> loadArtists(c));
            listContainer.getChildren().add(btn);
        }
    }

    private void loadArtists(Models.Country country) {
        currentView = "ARTIST";
        selectionTitle.setText(country.name + " 的歌手");
        listContainer.getChildren().clear();

        for (Models.Artist a : country.artists) {
            Button btn = createListButton(a.name);
            btn.setOnAction(e -> loadSongs(a));
            listContainer.getChildren().add(btn);
        }
    }

    private void loadSongs(Models.Artist artist) {
        currentView = "SONG";
        selectionTitle.setText(artist.name + " 的熱門歌曲");
        listContainer.getChildren().clear();

        for (Models.Song s : artist.songs) {
            Button btn = createListButton("🎵 " + s.title);
            btn.setOnAction(e -> startLearning(s));
            listContainer.getChildren().add(btn);
        }
    }

    private Button createListButton(String text) {
        Button btn = new Button(text);
        btn.getStyleClass().add("list-button");
        btn.setPrefWidth(260);
        btn.setPrefHeight(50);
        return btn;
    }

    // --- 學習與音樂播放邏輯 ---
    private void startLearning(Models.Song song) {
        showPage("LEARN");
        currentView = "LEARN";
        this.currentSong = song;
        this.currentVocabIndex = 0;
        fullLyricsLabel.setText(song.fullLyrics);

        playButton.setText("▶ 播放音樂");
        isPlaying = false;

        // 🌟 初始化進度條狀態
        progressBar.setValue(0);
        currentTimeLabel.setText("00:00");
        totalTimeLabel.setText("00:00");

        if (mediaPlayer != null) mediaPlayer.dispose();

        try {
            URL resource = getClass().getResource("/com/poplingo/poplingo/audio/" + song.audioFileName);
            if (resource != null) {
                Media media = new Media(resource.toString());
                mediaPlayer = new MediaPlayer(media);

                // 🌟 1. 當音樂載入完成時，設定進度條的最大值與總時間
                mediaPlayer.setOnReady(() -> {
                    Duration totalDuration = mediaPlayer.getTotalDuration();
                    progressBar.setMax(totalDuration.toSeconds());
                    totalTimeLabel.setText(formatTime(totalDuration));
                });

                // 🌟 2. 音樂播放時，自動推進滑桿與更新當前時間
                mediaPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
                    if (!progressBar.isValueChanging()) { // 如果使用者沒有在拖曳滑桿
                        progressBar.setValue(newTime.toSeconds());
                        currentTimeLabel.setText(formatTime(newTime));
                    }
                });

                // 🌟 3. 使用者拖曳滑桿時，改變音樂播放進度
                progressBar.valueChangingProperty().addListener((obs, wasChanging, isChanging) -> {
                    if (!isChanging) { // 拖曳放開的瞬間
                        mediaPlayer.seek(Duration.seconds(progressBar.getValue()));
                    }
                });

                // 🌟 4. 使用者直接點擊進度條某處時，直接跳轉
                progressBar.setOnMouseClicked(event -> {
                    mediaPlayer.seek(Duration.seconds(progressBar.getValue()));
                });

                // 當音樂播放完畢時，重置按鈕
                mediaPlayer.setOnEndOfMedia(() -> {
                    playButton.setText("▶ 播放音樂");
                    isPlaying = false;
                    progressBar.setValue(0);
                    mediaPlayer.seek(Duration.ZERO);
                });
            }
        } catch (Exception e) {
            System.out.println("找不到音樂檔案: " + song.audioFileName);
        }

        showVocab();
    }

    private String formatTime(Duration duration) {
        int totalSeconds = (int) Math.floor(duration.toSeconds());
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    @FXML
    protected void onPlayMusicClick() {
        if (mediaPlayer == null) {
            playButton.setText("找不到音檔");
            return;
        }

        if (isPlaying) {
            mediaPlayer.pause();
            playButton.setText("▶ 播放音樂");
        } else {
            mediaPlayer.play();
            playButton.setText("⏸ 暫停播放");
        }
        isPlaying = !isPlaying;
    }

    private void showVocab() {
        if (currentVocabIndex < currentSong.vocabList.size()) {
            Models.Vocabulary v = currentSong.vocabList.get(currentVocabIndex);
            learnTitle.setText("學習中: " + currentSong.title);
            wordLabel.setText(v.word);
            phoneticLabel.setText(v.phonetic);
            translationLabel.setText(v.translation);
            lyricLabel.setText(v.lyric);
        } else {
            wordLabel.setText("🎉 完成！");
            phoneticLabel.setText("");
            translationLabel.setText("你已學完這首歌的單字");
            lyricLabel.setText("");
        }
    }

    @FXML
    protected void onNextWordClick() {
        currentVocabIndex++;
        showVocab();
    }
}