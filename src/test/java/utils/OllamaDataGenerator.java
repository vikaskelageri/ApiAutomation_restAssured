package utils;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class OllamaDataGenerator {

    public static String getPayloadFromOllama() {
        try {
            URL url = new URL("http://localhost:11434/api/generate");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");

            // Explicitly ask for a raw JSON object without explanations, with the closing bracket
            String prompt = "Generate a realistic JSON object with the following fields, outputting only the JSON without any additional text or code blocks:\\n" +
                    "{\\n" +
                    "  \"company_name\": (string),\\n" +
                    "  \"email\": (string),\\n" +
                    "  \"first_name\": (string),\\n" +
                    "  \"is_physical_mailing_same\": (boolean),\\n" +
                    "  \"last_name\": (string),\\n" +
                    "  \"mailing_address\": { \"address\": (string), \"state\": (string), \"city\": (string), \"postal_code\": (string) },\\n" +
                    "  \"new_comm\": (integer, value less than or equal to 100),\\n" +
                    "  \"phone\": (string),\\n" +
                    "  \"physical_address\": { \"address\": (string), \"state\": (string), \"city\": (string), \"postal_code\": (string) },\\n" +
                    "  \"role\": \"AGENT\"\\n" +
                    "}";

            String body = "{\n" +
                    "  \"model\": \"gemma:2b\",\n" +
                    "  \"prompt\": \"" + prompt.replace("\"", "\\\"") + "\",\n" +
                    "  \"stream\": false\n" +
                    "}";

            // Log the request body being sent
            System.out.println("Request Body: " + body);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(body.getBytes());
                os.flush();
            }

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                // Log the non-200 status code
                System.out.println("Ollama returned non-200 status: " + responseCode);

                // Log the error stream (if any)
                try (Scanner errorScanner = new Scanner(conn.getErrorStream()).useDelimiter("\\A")) {
                    String errorResponse = errorScanner.hasNext() ? errorScanner.next() : "";
                    System.out.println("Error Response: " + errorResponse);
                } catch (Exception e) {
                    System.out.println("Error reading the error stream: " + e.getMessage());
                }

                return getStaticFallbackPayload();
            }

            // Read the response from the server
            Scanner scanner = new Scanner(conn.getInputStream()).useDelimiter("\\A");
            String response = scanner.hasNext() ? scanner.next() : "";

            // Directly return the 'response' part, which should be the raw JSON
            int start = response.indexOf("\"response\":\"") + 12;
            int end = response.indexOf("\",\"done\"");
            String rawJson = response.substring(start, end)
                    .replace("\\n", "\n")
                    .replace("\\\"", "\"");

            System.out.println("rawJson: " + rawJson);
            return rawJson;

        } catch (Exception e) {
            System.out.println("Error occurred while connecting to Ollama: " + e.getMessage());
            e.printStackTrace(); // Print full stack trace for better debugging
            return getStaticFallbackPayload();
        }
    }

    private static String getStaticFallbackPayload() {
        return "{\n" +
                "  \"company_name\": \"TechCorp\",\n" +
                "  \"email\": \"john.doe@techcorp.com\",\n" +
                "  \"first_name\": \"John\",\n" +
                "  \"is_physical_mailing_same\": true,\n" +
                "  \"last_name\": \"Doe\",\n" +
                "  \"mailing_address\": {\n" +
                "    \"address\": \"123 Maple Street\",\n" +
                "    \"state\": \"CA\",\n" +
                "    \"city\": \"Los Angeles\",\n" +
                "    \"postal_code\": \"90001\"\n" +
                "  },\n" +
                "  \"new_comm\": 50,\n" +
                "  \"phone\": \"123-456-7890\",\n" +
                "  \"physical_address\": {\n" +
                "    \"address\": \"123 Maple Street\",\n" +
                "    \"state\": \"CA\",\n" +
                "    \"city\": \"Los Angeles\",\n" +
                "    \"postal_code\": \"90001\"\n" +
                "  },\n" +
                "  \"role\": \"AGENT\"\n" +
                "}";
    }
}