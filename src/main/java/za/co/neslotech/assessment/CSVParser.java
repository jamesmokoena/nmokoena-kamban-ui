package za.co.neslotech.assessment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {
    // Method to parse CSV file and return a list of ExchangeRate objects
    public List<ExchangeRate> parseCSV(String filePath) {
        List<ExchangeRate> exchangeRates = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    String baseCurrency = data[0].trim();
                    String targetCurrency = data[1].trim();
                    try {
                        double rate = Double.parseDouble(data[2].trim());
                        exchangeRates.add(new ExchangeRate(baseCurrency, targetCurrency, rate));
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing exchange rate: " + e.getMessage());
                    }
                } else {
                    System.err.println("Invalid exchange rate format: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
        return exchangeRates;
    }

}
