package boa.entrega.supplier.controller;

import boa.entrega.supplier.controller.dto.CreateApiKeyRequestDTO;
import boa.entrega.supplier.controller.dto.CreateApiKeyResponseDTO;
import boa.entrega.supplier.controller.dto.DeleteResponseDTO;
import boa.entrega.supplier.model.ApiKey;
import boa.entrega.supplier.service.SecurityService;
import boa.entrega.supplier.utils.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(description = "Obtem todas API Keys ativas para o fornecedor logado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "API Keys retornadas"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("apikey")
    public ResponseEntity<List<ApiKey>> getApiKeys(@Parameter(hidden = true) @RequestHeader String authorization) {
        long supplierId = Long.parseLong(jwtUtils.getClaim(authorization, "supplier_id").toString());

        List<ApiKey> response = securityService.getApiKeys(supplierId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(description = "Gera uma nova API Key do fornecedor logado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "API Key gerada com sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(schema = @Schema(hidden = true)))
    })
    @PostMapping("apikey")
    public ResponseEntity<CreateApiKeyResponseDTO> createApiKey(@Parameter(hidden = true) @RequestHeader String authorization,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Informações de API Key") @RequestBody CreateApiKeyRequestDTO body) {
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

    @Operation(description = "Apaga a API Key informada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "API Key apagada com sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", description = "A API Key informada não existe", content = @Content(schema = @Schema(hidden = true)))
    })
    @DeleteMapping("apikey/{apiKeyId}")
    public ResponseEntity<DeleteResponseDTO> deleteApiKey(
            @Parameter(in = ParameterIn.PATH, description = "ID da API Key") @PathVariable(required = true) long apiKeyId) {
        securityService.deleteApiKey(apiKeyId);

        return new ResponseEntity<>(
                DeleteResponseDTO.builder().status("deleted").build(),
                HttpStatus.OK);
    }
}
