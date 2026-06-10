package com.poplingo.poplingo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Database {

    // 🌟 你的 LEMP 伺服器 API 網址
    private static final String API_BASE_URL = "https://mofumofu.ddns.net/api.php?action=";
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final Gson gson = new Gson();

    public static List<Models.Country> getAllCountries() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_BASE_URL + "get_countries"))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return gson.fromJson(response.body(), new TypeToken<List<Models.Country>>(){}.getType());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static Models.Vocabulary searchWord(int songId, String searchWord) {
        try {
            // 對中文/日文等字串進行 URL 編碼
            String encodedWord = URLEncoder.encode(searchWord, StandardCharsets.UTF_8);
            String url = API_BASE_URL + "search_word&song_id=" + songId + "&word=" + encodedWord;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200 && !response.body().trim().equals("null")) {
                return gson.fromJson(response.body(), Models.Vocabulary.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean loginUser(String username, String password) {
        try {
            JsonObject json = new JsonObject();
            json.addProperty("username", username);
            json.addProperty("password", password);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_BASE_URL + "login"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json.toString()))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                JsonObject resJson = gson.fromJson(response.body(), JsonObject.class);
                return resJson.get("success").getAsBoolean();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean registerUser(String username, String password) {
        try {
            JsonObject json = new JsonObject();
            json.addProperty("username", username);
            json.addProperty("password", password);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_BASE_URL + "register"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json.toString()))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                JsonObject resJson = gson.fromJson(response.body(), JsonObject.class);
                return resJson.get("success").getAsBoolean();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<Models.Vocabulary> getVocabsByCountry(int countryId) {
        try {
            String url = API_BASE_URL + "get_vocabs&country_id=" + countryId;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return gson.fromJson(response.body(), new TypeToken<List<Models.Vocabulary>>(){}.getType());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static void updateStarStatus(int vocabId, boolean isStarred) {
        try {
            JsonObject json = new JsonObject();
            json.addProperty("vocab_id", vocabId);
            json.addProperty("is_starred", isStarred);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_BASE_URL + "update_star"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json.toString()))
                    .build();

            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}