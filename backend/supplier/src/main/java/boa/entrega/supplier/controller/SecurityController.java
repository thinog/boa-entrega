package boa.entrega.supplier.controller;

import boa.entrega.supplier.controller.dto.CreateApiKeyRequestDTO;
import boa.entrega.supplier.controller.dto.DeleteResponseDTO;
import boa.entrega.supplier.model.ApiKey;
import boa.entrega.supplier.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("security")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityController {

    private final SecurityService securityService;

    @GetMapping("apikey/all")
    public ResponseEntity<List<ApiKey>> getApiKeys() {
        List<ApiKey> response = securityService.getApiKeys(0);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("apikey")
    public ResponseEntity<ApiKey> createCustomer(@RequestBody CreateApiKeyRequestDTO body) {
        ApiKey response = securityService.createApiKey(body.getKeyName(), body.getAccessType(), 0);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("apikey/{apiKeyId}")
    public ResponseEntity<DeleteResponseDTO> deleteApiKey(@RequestParam long apiKeyId) {
        securityService.deleteApiKey(apiKeyId);

        return new ResponseEntity<>(
                DeleteResponseDTO.builder().status("deleted").build(),
                HttpStatus.OK);
    }
}
