module com.poplingo.poplingo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media; // 🌟 補上這行：允許使用音樂播放模組

    opens com.poplingo.poplingo to javafx.fxml;
    exports com.poplingo.poplingo;
}