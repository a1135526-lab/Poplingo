module com.poplingo.poplingo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    // 🌟 新增 HTTP 與 JSON 解析模組
    requires java.net.http;
    requires com.google.gson;

    // 🌟 允許 Gson 反射讀取我們的物件
    opens com.poplingo.poplingo to javafx.fxml, com.google.gson;
    exports com.poplingo.poplingo;
}