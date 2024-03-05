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
        try (
            BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            // Check if the line contains exactly 3 elements (baseCurrency, targetCurrency, rate)
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                // Extract baseCurrency, targetCurrency, and rate from the array
                if (data.length == 3) {
                    String baseCurrency = data[0].trim(); // Trim to remove leading and trailing whitespace
                    String targetCurrency = data[1].trim();

                    double rate = Double.parseDouble(data[2].trim()); // Parse rate to double
                    // Create an ExchangeRate object using the extracted data and add it to the list
                    exchangeRates.add(new ExchangeRate(baseCurrency, targetCurrency, rate));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return exchangeRates;
    }
}
