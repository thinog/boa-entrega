package boa.entrega.supplier.client;

import org.springframework.stereotype.Component;

@Component
public class KeycloakClient {
    public String createApiKey(String apiKey, long supplierId) {
        return "";
    }

    public void disableApiKey(long apiKeyId, long supplierId) {

    }
}
