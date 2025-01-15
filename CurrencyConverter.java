import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class CurrencyConverter {

    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";
    private static final String BASE_CURRENCY = "BRL";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println("Bem-vindo ao Conversor de Moedas!");

        while (!exit) {
            System.out.println("\nSelecione uma opção:");
            System.out.println("1. Converter de Reais para outra moeda");
            System.out.println("2. Converter de outra moeda para Reais");
            System.out.println("3. Sair");
            System.out.print("Opção: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (option) {
                case 1 -> convertFromReais(scanner);
                case 2 -> convertToReais(scanner);
                case 3 -> {
                    System.out.println("Encerrando o programa. Até logo!");
                    exit = true;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }

    private static void convertFromReais(Scanner scanner) {
        System.out.println("\nSelecione a moeda de destino:");
        System.out.println("1. Dólar (USD)");
        System.out.println("2. Euro (EUR)");
        System.out.println("3. Libras Esterlinas (GBP)");
        System.out.println("4. Peso Argentino (ARS)");
        System.out.println("5. Peso Chileno (CLP)");
        System.out.print("Opção: ");

        int currencyOption = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        String targetCurrency = getCurrencyCode(currencyOption);
        if (targetCurrency == null) {
            System.out.println("Moeda inválida.");
            return;
        }

        System.out.print("Digite o valor em Reais: R$ ");
        double amount = scanner.nextDouble();

        double rate = fetchExchangeRate(BASE_CURRENCY, targetCurrency);
        if (rate != -1) {
            double convertedAmount = amount * rate;
            System.out.printf("Valor convertido: %.2f %s\n", convertedAmount, targetCurrency);
        } else {
            System.out.println("Erro ao obter a taxa de câmbio. Tente novamente mais tarde.");
        }
    }

    private static void convertToReais(Scanner scanner) {
        System.out.println("\nSelecione a moeda de origem:");
        System.out.println("1. Dólar (USD)");
        System.out.println("2. Euro (EUR)");
        System.out.println("3. Libras Esterlinas (GBP)");
        System.out.println("4. Peso Argentino (ARS)");
        System.out.println("5. Peso Chileno (CLP)");
        System.out.print("Opção: ");

        int currencyOption = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        String sourceCurrency = getCurrencyCode(currencyOption);
        if (sourceCurrency == null) {
            System.out.println("Moeda inválida.");
            return;
        }

        System.out.print("Digite o valor na moeda selecionada: ");
        double amount = scanner.nextDouble();

        double rate = fetchExchangeRate(sourceCurrency, BASE_CURRENCY);
        if (rate != -1) {
            double convertedAmount = amount * rate;
            System.out.printf("Valor convertido: R$ %.2f\n", convertedAmount);
        } else {
            System.out.println("Erro ao obter a taxa de câmbio. Tente novamente mais tarde.");
        }
    }

    private static String getCurrencyCode(int option) {
        return switch (option) {
            case 1 -> "USD";
            case 2 -> "EUR";
            case 3 -> "GBP";
            case 4 -> "ARS";
            case 5 -> "CLP";
            default -> null;
        };
    }

    private static double fetchExchangeRate(String fromCurrency, String toCurrency) {
        try {
            String url = API_URL + fromCurrency;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
                JsonObject rates = jsonResponse.getAsJsonObject("rates");
                return rates.get(toCurrency).getAsDouble();
            } else {
                System.out.println("Erro: " + response.statusCode() + " - " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Erro ao conectar com a API: " + e.getMessage());
        }

        return -1;
    }
}
