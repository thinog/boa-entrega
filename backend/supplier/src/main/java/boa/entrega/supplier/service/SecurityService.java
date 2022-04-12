package boa.entrega.supplier.service;

import boa.entrega.supplier.model.ApiKey;
import boa.entrega.supplier.repository.ApiKeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityService {
    private final ApiKeyRepository apiKeyRepository;
}
