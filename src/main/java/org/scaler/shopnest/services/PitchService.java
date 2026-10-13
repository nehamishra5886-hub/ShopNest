package org.scaler.shopnest.services;

import org.scaler.shopnest.dto.GroqPitchResponseDto;
import org.scaler.shopnest.dto.ProductPitchDto;
import org.scaler.shopnest.models.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.restclient.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class PitchService implements IPitchService {

    private static final String GROQ_URL= "https://api.groq.com/openai/v1/chat/completions";
    //private static final String API_KEY =
    private static final String MODEL = "openai/gpt-oss-20b";
    //private static final String SYSTEM_PROMPT = "You are a product pitch generator. You will be given a product name and description, and you will generate a short, catchy pitch for the product. " +
    //        "The pitch should be no more than 30 words. Do not include the product name in the pitch.";
    private static final String SYSTEM_PROMPT = "You are a marketing copywriter for ShopNest, an online store. " +
            "You will be given a product, and you will generate a short, catchy pitch for the product for push notifications" +
            " that make people want to open the app. The pitch should be no more than 30 words. ";

    private final RestTemplate restTemplate;
    private final String apiKey;

    public PitchService(RestTemplateBuilder restTemplateBuilder, @Value("${groq.api.key}") String apiKey) {
        this.restTemplate = restTemplateBuilder.build();
        this.apiKey = apiKey;
    }

    public String generatePitch(Product product) {
        System.out.println("product from generatePitch: " + product);
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(apiKey);
            headers.setContentType(MediaType.APPLICATION_JSON);
            Map<String, Object> body = Map.of(
                    "model", MODEL,
                    "reasoning_effort", "low",
                    "max_completion_tokens", 300,
                    "messages", List.of(
                            Map.of("role", "system", "content", SYSTEM_PROMPT),
                            Map.of("role", "user", "content", buildPrompt(product))));

            ResponseEntity<GroqPitchResponseDto> response = restTemplate.postForEntity(GROQ_URL,
                    new HttpEntity<>(body,headers), GroqPitchResponseDto.class);
            if(response.getStatusCode().is2xxSuccessful() && response.hasBody()) {
                return response.getBody().getChoices().get(0).getMessage().getContent();
            }
            throw new IllegalArgumentException("Pitch couldn't be generated");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return "New arrival: " + product.getName() +
                    " for just $" + product.getPrice() + ". Check it out!";
        }
    }

    private String buildPrompt(Product product) {
        System.out.println("product  " + product);
        return """
                Write ONE push notification for this product.
                Rules:
                - Maximum 25 words.
                - Mention the price.
                - At most one emoji.
                - No hashtags.
                - Reply with only the notification text.

                Product: %s
               
                Price: $%s
                Details: %s""".formatted(product.getName(),
                product.getPrice(), product.getDescription());
    }
}
