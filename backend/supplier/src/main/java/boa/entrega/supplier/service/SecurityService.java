package boa.entrega.supplier.service;

import boa.entrega.supplier.exception.EntityNotFoundException;
import boa.entrega.supplier.model.ApiKey;
import boa.entrega.supplier.repository.ApiKeyRepository;
import boa.entrega.supplier.utils.HashUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityService {

    private final ApiKeyRepository apiKeyRepository;
    private final HashUtils hashUtils;

    public ApiKey createApiKey(String keyName, ApiKey.AccessType accessType, long supplierId) {
        String key = hashUtils.generateUUIDv4();
        String hash = hashUtils.generateHashSha256(key);

        ApiKey apiKey = ApiKey.builder()
                .name(keyName)
                .hash(hash)
                .build();

        apiKeyRepository.save(apiKey);

        return apiKey;
    }

    public void deleteApiKey(long apiKeyId) {
        ApiKey apiKey = apiKeyRepository.findById(apiKeyId).orElseThrow(() -> new EntityNotFoundException("apikey"));
        apiKey.setActive(false);
        apiKeyRepository.save(apiKey);
    }

    public List<ApiKey> getApiKeys(long supplierId) {
        return apiKeyRepository.getAllBySupplierId(supplierId);
    }
}
