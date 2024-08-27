package malakhov.kt_practice.p2_web_practice.warmup;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

public class Solution {
    public static void main(String[] args) {
        RestClient restClient = RestClient.create();

        MyBody body = new MyBody("Yevheni", "Malakhov", "Irpin", List.of("Footbal", "Tenis"));
        ResponseEntity<Void> response = restClient.post()
                .uri("https://c2da-91-226-254-106.ngrok-free.app/here")
                .contentType(APPLICATION_JSON)
                .body(body)
                .retrieve()
                .toBodilessEntity();

        System.out.println(response);
    }
}
