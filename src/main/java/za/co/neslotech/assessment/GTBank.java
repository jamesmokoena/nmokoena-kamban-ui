package za.co.neslotech.assessment;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GTBank {
    private APIIntegration apiIntegration;
    private Map<String, Double> exchangeRates;

    public GTBank() {
        this.apiIntegration = new APIIntegration();
        this.exchangeRates = new HashMap<>();
    }

    public void fetchExchangeRates() {
        this.exchangeRates = apiIntegration.getExchangeRates();
        System.out.println("Exchange rates fetched successfully.");

        // Write exchange rates to a text file
        try (FileWriter writer = new FileWriter("exchange_rates.txt")) {
            writer.write("Exchange rates:\n");
            for (Map.Entry<String, Double> entry : exchangeRates.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue() + "\n");
            }
            System.out.println("Exchange rates written to exchange_rates.txt.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to write exchange rates to file.");
        }
    }


    // Other methods for performing banking operations
    // (e.g., methods for transferring funds, managing accounts, etc.)

    public static void main(String[] args) {
        // Create an instance of GTBank
        GTBank gtBank = new GTBank();

        // Fetch exchange rates
        gtBank.fetchExchangeRates();

        // You can add additional logic or method calls here as needed
    }
}

