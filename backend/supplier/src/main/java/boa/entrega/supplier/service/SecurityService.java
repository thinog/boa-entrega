package boa.entrega.supplier.service;

import boa.entrega.supplier.client.KeycloakClient;
import boa.entrega.supplier.exception.EntityNotFoundException;
import boa.entrega.supplier.messaging.publish.MessagePublisher;
import boa.entrega.supplier.model.ApiKey;
import boa.entrega.supplier.model.Event;
import boa.entrega.supplier.repository.ApiKeyRepository;
import boa.entrega.supplier.utils.HashUtils;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityService {

    private final ApiKeyRepository apiKeyRepository;
    private final HashUtils hashUtils;
    private final KeycloakClient keycloakClient;
    private final MessagePublisher messagePublisher;

    public ApiKey createApiKey(String keyName, ApiKey.AccessType accessType, long supplierId) {
        String key = hashUtils.generateUUIDv4();
        String hash = hashUtils.generateHashSha256(key);

        String externalId = keycloakClient.createApiKey(key, supplierId);

        ApiKey apiKey = ApiKey.builder()
                .supplierId(supplierId)
                .externalId(externalId)
                .name(keyName)
                .hash(hash)
                .accessType(accessType)
                .build();

        apiKeyRepository.save(apiKey);

        messagePublisher.publish(Event.builder().eventType("API_KEY_CREATED").body(apiKey).build(), "queue-url");

        return apiKey;
    }

    public void deleteApiKey(long apiKeyId) {
        ApiKey apiKey = apiKeyRepository.findById(apiKeyId).orElseThrow(() -> new EntityNotFoundException("apikey"));
        apiKey.setActive(false);
        apiKeyRepository.save(apiKey);
        messagePublisher.publish(Event.builder().eventType("API_KEY_DELETED").body(apiKey).build(), "queue-url");
    }

    public List<ApiKey> getApiKeys(long supplierId) {
        return apiKeyRepository.getAllBySupplierId(supplierId);
    }
}
