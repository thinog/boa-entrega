package boa.entrega.supplier.client;

import org.springframework.stereotype.Component;

@Component
public class KeycloakClient {
    /***
     * Creates a new Client Credentials Grant
     * @param apiKey
     * @param supplierId
     * @return client id
     */
    public String createApiKey(String apiKey, long supplierId) {
        return "";
    }

    public void disableApiKey(String keycloakKeyId, long supplierId) {

    }
}
