package za.co.neslotech.assessment;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class APIIntegration {
    private static final String API_KEY = "55e327fee782c2e1d12c6441689d8e42";
    private static final String BASE_URL = "http://data.fixer.io/api/latest?access_key=" + API_KEY;

    public Map<String, Double> getExchangeRates() {
        Map<String, Double> exchangeRates = new HashMap<>();
        try {
            URL url = new URL(BASE_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse JSON response using Gson library
            JsonObject jsonResponse = JsonParser.parseString(response.toString()).getAsJsonObject();
            JsonObject rates = jsonResponse.getAsJsonObject("rates");
            for (Map.Entry<String, JsonElement> entry : rates.entrySet()) {
                String currencyCode = entry.getKey();
                Double rate = Double.parseDouble(entry.getValue().toString());
                exchangeRates.put(currencyCode, rate);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return exchangeRates;
    }
}