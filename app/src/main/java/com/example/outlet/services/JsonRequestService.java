package com.example.outlet.services;



import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class JsonRequestService {

    private String readAll(Reader bufferedReader) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int currentChar;
        while ((currentChar = bufferedReader.read()) != -1) {
            stringBuilder.append((char) currentChar);
        }
        return stringBuilder.toString();
    }

    public JSONObject readJsonFromUrl(String url) {
        JSONObject json = null;

        try (InputStream inputStream = new URL(url).openStream()) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String jsonText = readAll(bufferedReader);
            json = new JSONObject(jsonText);

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
}
