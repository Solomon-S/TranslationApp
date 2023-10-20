package edu.bsu.cs222;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class TranslationAPIConnection {
    public static String translate(String text) throws IOException {
        // INSERT YOU URL HERE
        String urlStr = "https://script.google.com/macros/s/AKfycbwcbt45ONMpRvozF1ZFT0pAmVVXEeU0feqh3S4Vus8cGIizb8S0Lqf4q-2PMTYOJIkR/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + "es" +
                "&source=" + "en";
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

}

