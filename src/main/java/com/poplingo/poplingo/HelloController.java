package com.poplingo.poplingo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;

import java.net.URL;
import java.util.Collections;
import java.util.List;

public class HelloController {

    @FXML private VBox pageLogin, pageMode, pageSelection, pageLearn, pageReview, listContainer;
    @FXML private Label selectionTitle, learnTitle, wordLabel, phoneticLabel, translationLabel, lyricLabel;
    @FXML private TextFlow lyricsTextFlow;
    @FXML private Button playButton;
    @FXML private Slider progressBar;
    @FXML private Label currentTimeLabel, totalTimeLabel;

    // 🌟 學習介面收藏鈕綁定
    @FXML private Button starButton;

    // 複習系統元件
    @FXML private Label reviewProgressLabel, reviewWordLabel, reviewPhoneticLabel, reviewTranslationLabel, reviewSongLabel, reviewHintLabel;

    private Models.Song currentSong;
    private MediaPlayer mediaPlayer;
    private boolean isPlaying = false;

    // 狀態記憶變數
    private String currentViewState = "LOGIN";
    private boolean isReviewMode = false;
    private Models.Country currentCountry;
    private Models.Artist currentArtist;

    // 當前畫面正在看見的單字快取（供星號收藏切換使用）
    private Models.Vocabulary currentDisplayedVocab;

    // 複習卡片資料
    private List<Models.Vocabulary> currentReviewList;
    private int currentReviewIndex = 0;
    private boolean isCardFlipped = false;

    private void showPage(String pageName) {
        pageLogin.setVisible(pageName.equals("LOGIN"));
        pageMode.setVisible(pageName.equals("MODE"));
        pageSelection.setVisible(pageName.equals("SELECT"));
        pageLearn.setVisible(pageName.equals("LEARN"));
        pageReview.setVisible(pageName.equals("REVIEW"));
    }

    @FXML protected void onLoginClick() {
        showPage("MODE");
        currentViewState = "MODE";
    }

    @FXML protected void onLearningModeClick() {
        isReviewMode = false;
        loadCountries();
    }

    @FXML protected void onReviewModeClick() {
        isReviewMode = true;
        loadCountries();
    }

    @FXML
    protected void onBackClick() {
        if (mediaPlayer != null) mediaPlayer.stop();

        switch (currentViewState) {
            case "LEARN": loadSongs(currentArtist); break;
            case "REVIEW": loadCountries(); break;
            case "SONGS": loadArtists(currentCountry); break;
            case "ARTISTS": loadCountries(); break;
            case "COUNTRIES":
                showPage("MODE");
                currentViewState = "MODE";
                break;
            case "MODE":
                showPage("LOGIN");
                currentViewState = "LOGIN";
                break;
        }
    }

    private void loadCountries() {
        currentViewState = "COUNTRIES";
        showPage("SELECT");
        selectionTitle.setText(isReviewMode ? "選擇複習的語言" : "探索音樂世界");
        listContainer.getChildren().clear();

        for (Models.Country c : Database.getAllCountries()) {
            Button btn = createListButton(c.name);

            // 🌟 新增：嘗試讀取與國家名稱相同的圖片
            try {
                URL imgUrl = getClass().getResource("/com/poplingo/poplingo/images/" + c.name + ".png");
                if (imgUrl != null) {
                    // 建立圖片元件
                    Image flagImage = new Image(imgUrl.toExternalForm());
                    ImageView flagView = new ImageView(flagImage);

                    // 設定國旗的大小
                    flagView.setFitWidth(35);  // 寬度
                    flagView.setFitHeight(25); // 高度
                    flagView.setPreserveRatio(true); // 保持比例不變形

                    // 將圖片塞入按鈕，並設定圖片與文字的距離
                    btn.setGraphic(flagView);
                    btn.setGraphicTextGap(15);
                }
            } catch (Exception e) {
                System.out.println("找不到國旗圖片：" + c.name + ".png");
            }

            btn.setOnAction(e -> {
                if (isReviewMode) startReview(c);
                else loadArtists(c);
            });
            listContainer.getChildren().add(btn);
        }
    }

    private void loadArtists(Models.Country country) {
        this.currentCountry = country;
        currentViewState = "ARTISTS";
        showPage("SELECT");
        selectionTitle.setText(country.name + " 的歌手");
        listContainer.getChildren().clear();
        for (Models.Artist a : country.artists) {
            Button btn = createListButton(a.name);
            btn.setOnAction(e -> loadSongs(a));
            listContainer.getChildren().add(btn);
        }
    }

    private void loadSongs(Models.Artist artist) {
        this.currentArtist = artist;
        currentViewState = "SONGS";
        showPage("SELECT");
        selectionTitle.setText(artist.name + " 的歌曲");
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
        btn.setPrefWidth(260); btn.setPrefHeight(50);
        return btn;
    }

    // ==========================================
    // 🌟 點擊收藏按鈕動作
    // ==========================================
    @FXML
    protected void onStarClick() {
        if (currentDisplayedVocab != null) {
            // 切換反轉狀態
            currentDisplayedVocab.isStarred = !currentDisplayedVocab.isStarred;
            // 更新進 MySQL 資料庫
            Database.updateStarStatus(currentDisplayedVocab.id, currentDisplayedVocab.isStarred);
            // 即時反應介面圖示
            starButton.setText(currentDisplayedVocab.isStarred ? "★" : "☆");
        }
    }

    // ==========================================
    // 複習模式邏輯
    // ==========================================
    private void startReview(Models.Country country) {
        this.currentCountry = country;
        currentViewState = "REVIEW";
        showPage("REVIEW");

        // 固定傳入對應國家代號 (1:韓國, 2:日本, 3:菲律賓)
        int idMapping = country.name.equals("Korea") ? 1 : (country.name.equals("Japan") ? 2 : 3);
        currentReviewList = Database.getVocabsByCountry(idMapping);
        Collections.shuffle(currentReviewList);

        currentReviewIndex = 0;
        updateReviewCard();
    }

    private void updateReviewCard() {
        if (currentReviewList == null || currentReviewList.isEmpty()) {
            reviewWordLabel.setText("尚無收藏單字");
            reviewPhoneticLabel.setText("");
            reviewProgressLabel.setText("0 / 0");
            reviewTranslationLabel.setVisible(false);
            reviewSongLabel.setVisible(false);
            reviewHintLabel.setVisible(false);
            return;
        }

        Models.Vocabulary currentVocab = currentReviewList.get(currentReviewIndex);

        isCardFlipped = false;
        reviewWordLabel.setText(currentVocab.word);
        reviewPhoneticLabel.setText(currentVocab.phonetic != null ? currentVocab.phonetic : "");

        reviewTranslationLabel.setText(currentVocab.translation);
        reviewSongLabel.setText("🎵 來自: " + currentVocab.songTitle);

        reviewTranslationLabel.setVisible(false);
        reviewSongLabel.setVisible(false);
        reviewHintLabel.setVisible(true);

        reviewProgressLabel.setText((currentReviewIndex + 1) + " / " + currentReviewList.size());
    }

    @FXML protected void onFlipCard() {
        if (currentReviewList == null || currentReviewList.isEmpty()) return;
        isCardFlipped = !isCardFlipped;
        reviewTranslationLabel.setVisible(isCardFlipped);
        reviewSongLabel.setVisible(isCardFlipped);
        reviewHintLabel.setVisible(!isCardFlipped);
    }

    @FXML protected void onNextCardClick() {
        if (currentReviewList != null && currentReviewIndex < currentReviewList.size() - 1) {
            currentReviewIndex++;
            updateReviewCard();
        }
    }

    @FXML protected void onPrevCardClick() {
        if (currentReviewList != null && currentReviewIndex > 0) {
            currentReviewIndex--;
            updateReviewCard();
        }
    }

    // ==========================================
    // 音樂學習模式邏輯
    // ==========================================
    private static class LyricLine {
        double timeSeconds;
        java.util.List<Text> textNodes = new java.util.ArrayList<>();
        public LyricLine(double timeSeconds) { this.timeSeconds = timeSeconds; }
    }

    private java.util.List<LyricLine> lyricLines = new java.util.ArrayList<>();
    private int currentActiveLineIndex = -1;

    private void startLearning(Models.Song song) {
        showPage("LEARN");
        currentViewState = "LEARN";
        this.currentSong = song;

        wordLabel.setText("請點擊下方歌詞");
        phoneticLabel.setText("點擊單字查詢翻譯");
        translationLabel.setText("-");
        lyricLabel.setText("-");

        // 🌟 初始化未點選單字前，先隱藏星號按鈕
        starButton.setVisible(false);
        currentDisplayedVocab = null;

        lyricLines.clear();
        currentActiveLineIndex = -1;
        lyricsTextFlow.getChildren().clear();

        String[] lines = song.fullLyrics.split("\n");
        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty()) continue;

            double timeSeconds = 0.0;
            String lyricText = line;

            if (line.matches("\\[\\d{2}:\\d{2}\\.\\d{2}\\].*")) {
                String minStr = line.substring(1, 3);
                String secStr = line.substring(4, 9);
                timeSeconds = Integer.parseInt(minStr) * 60 + Double.parseDouble(secStr);
                lyricText = line.substring(10).trim();
            }

            LyricLine lyricLine = new LyricLine(timeSeconds);
            final String finalLyricText = lyricText;
            final double finalTimeSeconds = timeSeconds;

            String[] words = lyricText.split(" ");
            for (String word : words) {
                Text textNode = new Text(word + " ");
                textNode.getStyleClass().add("clickable-word");

                textNode.setOnMouseClicked(e -> {
                    handleWordClick(word, finalLyricText);
                    if (mediaPlayer != null) mediaPlayer.seek(Duration.seconds(finalTimeSeconds));
                });

                lyricLine.textNodes.add(textNode);
                lyricsTextFlow.getChildren().add(textNode);
            }
            lyricsTextFlow.getChildren().add(new Text("\n"));
            lyricLines.add(lyricLine);
        }

        progressBar.setValue(0);
        currentTimeLabel.setText("00:00");
        totalTimeLabel.setText("00:00");
        playButton.setText("▶ 播放音樂");
        isPlaying = false;

        if (mediaPlayer != null) mediaPlayer.dispose();
        try {
            URL resource = getClass().getResource("/com/poplingo/poplingo/audio/" + song.audioFileName);
            if (resource != null) {
                mediaPlayer = new MediaPlayer(new Media(resource.toString()));

                mediaPlayer.setOnReady(() -> {
                    Duration totalDuration = mediaPlayer.getTotalDuration();
                    progressBar.setMax(totalDuration.toSeconds());
                    totalTimeLabel.setText(formatTime(totalDuration));
                });

                mediaPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
                    if (!progressBar.isValueChanging()) {
                        progressBar.setValue(newTime.toSeconds());
                        currentTimeLabel.setText(formatTime(newTime));
                    }

                    double currentTime = newTime.toSeconds();
                    int activeIndex = -1;

                    for (int i = 0; i < lyricLines.size(); i++) {
                        if (currentTime >= lyricLines.get(i).timeSeconds) activeIndex = i;
                        else break;
                    }

                    if (activeIndex != currentActiveLineIndex) {
                        if (currentActiveLineIndex >= 0 && currentActiveLineIndex < lyricLines.size()) {
                            for (Text t : lyricLines.get(currentActiveLineIndex).textNodes) {
                                t.getStyleClass().remove("active-word");
                            }
                        }
                        if (activeIndex >= 0) {
                            for (Text t : lyricLines.get(activeIndex).textNodes) {
                                t.getStyleClass().add("active-word");
                            }
                        }
                        currentActiveLineIndex = activeIndex;
                    }
                });

                progressBar.valueChangingProperty().addListener((obs, wasChanging, isChanging) -> {
                    if (!isChanging) mediaPlayer.seek(Duration.seconds(progressBar.getValue()));
                });
                progressBar.setOnMouseClicked(event -> mediaPlayer.seek(Duration.seconds(progressBar.getValue())));

                mediaPlayer.setOnEndOfMedia(() -> {
                    playButton.setText("▶ 播放音樂");
                    isPlaying = false;
                    progressBar.setValue(0);
                    mediaPlayer.seek(Duration.ZERO);
                });
            }
        } catch (Exception e) { System.out.println("音檔讀取失敗"); }
    }

    private void handleWordClick(String rawWord, String fullLine) {
        String cleanWord = rawWord.replaceAll("[^\\p{L}0-9]", "");
        if (cleanWord.isEmpty()) return;

        Models.Vocabulary vocab = Database.searchWord(currentSong.id, cleanWord);
        if (vocab != null) {
            // 🌟 快取當前點選的單字
            currentDisplayedVocab = vocab;
            // 🌟 顯示收藏按鈕，並根據收藏狀態顯示對應圖示
            starButton.setVisible(true);
            starButton.setText(vocab.isStarred ? "★" : "☆");

            wordLabel.setText(vocab.word);
            phoneticLabel.setText(vocab.phonetic);
            translationLabel.setText(vocab.translation);
            lyricLabel.setText(vocab.lyric);
        } else {
            // 資料庫查無此字，不提供收藏
            starButton.setVisible(false);
            currentDisplayedVocab = null;

            wordLabel.setText(cleanWord);
            phoneticLabel.setText("...");
            translationLabel.setText("尚無此單字翻譯");
            lyricLabel.setText("♪ " + fullLine);
        }
    }

    @FXML protected void onPlayMusicClick() {
        if (mediaPlayer == null) return;
        if (isPlaying) { mediaPlayer.pause(); playButton.setText("▶ 播放音樂"); }
        else { mediaPlayer.play(); playButton.setText("⏸ 暫停播放"); }
        isPlaying = !isPlaying;
    }

    private String formatTime(Duration duration) {
        int totalSeconds = (int) Math.floor(duration.toSeconds());
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}