package boa.entrega.supplier.controller;

import boa.entrega.supplier.controller.dto.CreateApiKeyRequestDTO;
import boa.entrega.supplier.controller.dto.CreateApiKeyResponseDTO;
import boa.entrega.supplier.controller.dto.DeleteResponseDTO;
import boa.entrega.supplier.model.ApiKey;
import boa.entrega.supplier.service.SecurityService;
import boa.entrega.supplier.utils.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/security")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@SecurityRequirement(name = "bearerAuth")
public class SecurityController {

    private final SecurityService securityService;
    private final JwtUtils jwtUtils;

    @Operation(description = "Gets all API Keys for the logged supplier")
    @GetMapping("apikey")
    public ResponseEntity<List<ApiKey>> getApiKeys(@Parameter(hidden = true) @RequestHeader String authorization) {
        long supplierId = Long.parseLong(jwtUtils.getClaim(authorization, "supplier_id").toString());

        List<ApiKey> response = securityService.getApiKeys(supplierId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(description = "Creates a new API Key for the logged supplier")
    @PostMapping("apikey")
    public ResponseEntity<CreateApiKeyResponseDTO> createApiKey(@Parameter(hidden = true) @RequestHeader String authorization,
            @io.swagger.v3.oas.annotations.parameters.RequestBody @RequestBody CreateApiKeyRequestDTO body) {
        long supplierId = Long.parseLong(jwtUtils.getClaim(authorization, "supplier_id").toString());

        ApiKey response = securityService.createApiKey(body.getKeyName(), body.getAccessType(), supplierId);
        CreateApiKeyResponseDTO dto = CreateApiKeyResponseDTO.builder()
                .id(response.getId())
                .name(response.getName())
                .apiKey(response.getKey())
                .accessType(response.getAccessType())
                .createdAt(new Date())
                .build();

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @Operation(description = "Deletes a API Key")
    @DeleteMapping("apikey/{apiKeyId}")
    public ResponseEntity<DeleteResponseDTO> deleteApiKey(@PathVariable long apiKeyId) {
        securityService.deleteApiKey(apiKeyId);

        return new ResponseEntity<>(
                DeleteResponseDTO.builder().status("deleted").build(),
                HttpStatus.OK);
    }
}
