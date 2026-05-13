package com.poplingo.poplingo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;

import java.net.URL;

public class HelloController {

    @FXML private VBox pageLogin, pageSelection, pageLearn, listContainer;
    @FXML private Label selectionTitle, learnTitle, wordLabel, phoneticLabel, translationLabel, lyricLabel;
    @FXML private TextFlow lyricsTextFlow;
    @FXML private Button playButton;

    // 🌟 補回來的進度條綁定
    @FXML private Slider progressBar;
    @FXML private Label currentTimeLabel;
    @FXML private Label totalTimeLabel;

    private Models.Song currentSong;
    private MediaPlayer mediaPlayer;
    private boolean isPlaying = false;

    private void showPage(String pageName) {
        pageLogin.setVisible(pageName.equals("LOGIN"));
        pageSelection.setVisible(pageName.equals("SELECT"));
        pageLearn.setVisible(pageName.equals("LEARN"));
    }

    @FXML protected void onLoginClick() { loadCountries(); }

    @FXML
    protected void onBackClick() {
        if (mediaPlayer != null) mediaPlayer.stop();
        showPage("SELECT");
    }

    private void loadCountries() {
        showPage("SELECT");
        selectionTitle.setText("探索音樂世界");
        listContainer.getChildren().clear();
        for (Models.Country c : Database.getAllCountries()) {
            Button btn = createListButton(c.name);
            btn.setOnAction(e -> loadArtists(c));
            listContainer.getChildren().add(btn);
        }
    }

    private void loadArtists(Models.Country country) {
        selectionTitle.setText(country.name + " 的歌手");
        listContainer.getChildren().clear();
        for (Models.Artist a : country.artists) {
            Button btn = createListButton(a.name);
            btn.setOnAction(e -> loadSongs(a));
            listContainer.getChildren().add(btn);
        }
    }

    private void loadSongs(Models.Artist artist) {
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

//    private static class LyricLine {
//        double timeSeconds;
//        java.util.List<Text> textNodes = new java.util.ArrayList<>();
//        public LyricLine(double timeSeconds) { this.timeSeconds = timeSeconds; }
//    }

    // ... 前面的按鈕與畫面切換邏輯維持不變 ...

    // 🌟 新增：用來記錄每一行歌詞的秒數與它的文字節點
    private static class LyricLine {
        double timeSeconds;
        java.util.List<Text> textNodes = new java.util.ArrayList<>();
        public LyricLine(double timeSeconds) { this.timeSeconds = timeSeconds; }
    }

    // 🌟 新增：儲存當前歌曲的所有歌詞行，以及目前亮起的是哪一行
    private java.util.List<LyricLine> lyricLines = new java.util.ArrayList<>();
    private int currentActiveLineIndex = -1;

    // --- 學習與音樂播放邏輯 (同步歌詞升級版) ---
    private void startLearning(Models.Song song) {
        showPage("LEARN");
        this.currentSong = song;

        wordLabel.setText("請點擊下方歌詞");
        phoneticLabel.setText("點擊單字查詢翻譯");
        translationLabel.setText("-");
        lyricLabel.setText("-");

        // 1. 初始化狀態與清空畫面
        lyricLines.clear();
        currentActiveLineIndex = -1;
        lyricsTextFlow.getChildren().clear();

        // 2. 解析 LRC 格式並建立可點擊歌詞
        String[] lines = song.fullLyrics.split("\n");
        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty()) continue;

            double timeSeconds = 0.0;
            String lyricText = line;

            // 正規表達式：檢查是否符合 [mm:ss.xx] 格式
            if (line.matches("\\[\\d{2}:\\d{2}\\.\\d{2}\\].*")) {
                String minStr = line.substring(1, 3);
                String secStr = line.substring(4, 9);
                // 將分和秒轉換為總秒數 (例如 01:30.00 -> 90.0秒)
                timeSeconds = Integer.parseInt(minStr) * 60 + Double.parseDouble(secStr);
                // 擷取時間標籤後面的純歌詞字串
                lyricText = line.substring(10).trim();
            }

            LyricLine lyricLine = new LyricLine(timeSeconds);

            final String finalLyricText = lyricText;
            // 🌟 1. 新增一個常數分身，用來記錄這行歌詞的時間，傳給 Lambda 使用
            final double finalTimeSeconds = timeSeconds;

            String[] words = lyricText.split(" ");
            for (String word : words) {
                Text textNode = new Text(word + " ");
                textNode.getStyleClass().add("clickable-word");

                // 🌟 2. 將原本的一行點擊事件，改寫成多行：同時查單字 + 跳轉音樂時間
                textNode.setOnMouseClicked(e -> {
                    handleWordClick(word, finalLyricText); // 查單字並更新畫面上方

                    if (mediaPlayer != null) {
                        // 讓音樂播放器直接跳轉到該句歌詞的秒數
                        mediaPlayer.seek(Duration.seconds(finalTimeSeconds));
                    }
                });

                lyricLine.textNodes.add(textNode);
                lyricsTextFlow.getChildren().add(textNode);
            }
            lyricsTextFlow.getChildren().add(new Text("\n"));
            lyricLines.add(lyricLine); // 存入記憶體供播放時比對
        }

        // 3. 進度條初始化
        progressBar.setValue(0);
        currentTimeLabel.setText("00:00");
        totalTimeLabel.setText("00:00");
        playButton.setText("▶ 播放音樂");
        isPlaying = false;

        // 4. 音樂播放器與時間軸綁定
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

                // 🌟 音樂播放中：同時推進滑桿與判斷歌詞同步
                mediaPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
                    if (!progressBar.isValueChanging()) {
                        progressBar.setValue(newTime.toSeconds());
                        currentTimeLabel.setText(formatTime(newTime));
                    }

                    // --- 歌詞同步核心演算法 ---
                    double currentTime = newTime.toSeconds();
                    int activeIndex = -1;

                    // 尋找當前時間落在這首歌的哪一行
                    for (int i = 0; i < lyricLines.size(); i++) {
                        if (currentTime >= lyricLines.get(i).timeSeconds) {
                            activeIndex = i;
                        } else {
                            break; // 因為時間是遞增的，一旦歌詞時間大於當前時間就可以提早結束迴圈
                        }
                    }

                    // 如果「該亮的行」跟「目前亮的行」不一樣，就切換樣式
                    if (activeIndex != currentActiveLineIndex) {
                        // (A) 關閉前一行的亮色
                        if (currentActiveLineIndex >= 0 && currentActiveLineIndex < lyricLines.size()) {
                            for (Text t : lyricLines.get(currentActiveLineIndex).textNodes) {
                                t.getStyleClass().remove("active-word");
                            }
                        }
                        // (B) 開啟新的一行亮色
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

    // ... 後面的 handleWordClick, onPlayMusicClick 維持不變 ...

    private void handleWordClick(String rawWord, String fullLine) {
        String cleanWord = rawWord.replaceAll("[^a-zA-Z가-힣0-9]", "");
        if (cleanWord.isEmpty()) return;

        Models.Vocabulary vocab = Database.searchWord(currentSong.id, cleanWord);
        if (vocab != null) {
            wordLabel.setText(vocab.word);
            phoneticLabel.setText(vocab.phonetic);
            translationLabel.setText(vocab.translation);
            lyricLabel.setText(vocab.lyric);
        } else {
            wordLabel.setText(cleanWord);
            phoneticLabel.setText("...");
            translationLabel.setText("尚無此單字翻譯");
            lyricLabel.setText("♪ " + fullLine);
        }
    }

    @FXML
    protected void onPlayMusicClick() {
        if (mediaPlayer == null) return;
        if (isPlaying) { mediaPlayer.pause(); playButton.setText("▶ 播放音樂"); }
        else { mediaPlayer.play(); playButton.setText("⏸ 暫停播放"); }
        isPlaying = !isPlaying;
    }

    // 🌟 輔助方法：時間格式化
    private String formatTime(Duration duration) {
        int totalSeconds = (int) Math.floor(duration.toSeconds());
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}