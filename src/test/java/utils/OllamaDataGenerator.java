package utils;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class OllamaDataGenerator {

    private String sendPromptToOllama(String prompt) {
        try {
            URL url = new URL("http://localhost:11434/api/generate");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");

            // Construct the body directly without relying on string literals for newlines
            String body = String.format("{\"model\": \"%s\", \"prompt\": \"%s\", \"stream\": false}",
                    "gemma:2b",
                    prompt.replace("\"", "\\\""));

            // Log the exact request body being sent to Ollama
            System.out.println("Ollama Request Body:");
            System.out.println(body);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(body.getBytes());
                os.flush();
            }

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                System.out.println("Ollama returned non-200 status: " + responseCode);
                try (Scanner errorScanner = new Scanner(conn.getErrorStream()).useDelimiter("\\A")) {
                    String errorResponse = errorScanner.hasNext() ? errorScanner.next() : "";
                    System.out.println("Error Response: " + errorResponse);
                } catch (Exception e) {
                    System.out.println("Error reading the error stream: " + e.getMessage());
                }
                return getStaticFallbackPayload(); // Or handle error differently
            }

            Scanner scanner = new Scanner(conn.getInputStream()).useDelimiter("\\A");
            String response = scanner.hasNext() ? scanner.next() : "";

            int start = response.indexOf("\"response\":\"") + 12;
            int end = response.indexOf("\",\"done\"");
            return response.substring(start, end)
                    .replace("\\n", "\n")
                    .replace("\\\"", "\"");

        } catch (Exception e) {
            System.out.println("Error communicating with Ollama: " + e.getMessage());
            e.printStackTrace();
            return getStaticFallbackPayload(); // Or handle error differently
        }
    }
    public String generateSimilarPayload(String samplePayload) {
        String prompt = "Generate a simple JSON object with two keys: 'name' and 'age'.";
        return sendPromptToOllama(prompt);
    }

    // Existing getPayloadFromOllama() can be repurposed or kept for more specific generation
    // if needed. For this approach, sendPromptToOllama is more generic.

    private String getStaticFallbackPayload() {
        return "{}"; // Basic fallback
    }
}