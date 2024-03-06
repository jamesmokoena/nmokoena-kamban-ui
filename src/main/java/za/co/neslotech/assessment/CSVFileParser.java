package za.co.neslotech.assessment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVFileParser {

    public List<ExchangeRate> parse(String filePath) {

        List<ExchangeRate> exchangeRates = new ArrayList<>();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip the header line if necessary
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    String baseCurrency = data[0].trim();
                    String targetCurrency = data[1].trim();
                    double rate = Double.parseDouble(data[2].trim());
                    ExchangeRate exchangeRate = new ExchangeRate(baseCurrency, targetCurrency, rate);
                    exchangeRates.add(exchangeRate);
                } else {
                    System.out.println("Invalid line in CSV file: " + line);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return exchangeRates;
    }
    public void saveToCSV(List<ExchangeRate> exchangeRates, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (ExchangeRate rate : exchangeRates) {
                writer.write(rate.getBaseCurrency() + "," + rate.getTargetCurrency() + "," + rate.getRate() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
