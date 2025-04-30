package utils;



public class TestDataGenerator {

    private final OllamaDataGenerator ollamaGenerator = new OllamaDataGenerator();

    public String InvoiceCreditPay() {
        String basePayload = "{\n" +
                "  \"policy_id\": 208,\n" +
                "  \"received_on\": \"2025-04-25\",\n" +
                "  \"payment_source\": \"WIRE_TRANSFER\",\n" +
                "  \"amount\": 1000.84,\n" +
                "  \"payer_name\": \"Cert_Issue\",\n" +
                "  \"payer_email\": \"certissue@gmail.com\",\n" +
                "  \"remarks\": \"dsde2s\",\n" +
                "  \"epay_transaction_id\": 2334212\n" +
                "}";
        return ollamaGenerator.generateSimilarPayload(basePayload);
    }

    public String CreateQuote() {
        String basePayload = "{\n" +
                "  \"Company\": \"tata\",\n" +
                "  \"amount\": 2000\n" +
                "}";
        return ollamaGenerator.generateSimilarPayload(basePayload);
    }

    // Add more methods for other API payloads as needed
}