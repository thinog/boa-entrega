package boa.entrega.supplier.controller;

import boa.entrega.supplier.controller.dto.DeleteResponseDTO;
import boa.entrega.supplier.model.Warehouse;
import boa.entrega.supplier.service.WarehouseService;
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

import java.util.List;

@RestController
@RequestMapping("api/warehouse")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@SecurityRequirement(name = "bearerAuth")
public class WarehouseController {

    private final WarehouseService warehouseService;
    private final JwtUtils jwtUtils;

    @Operation(description = "Obtem todos os depósitos do fornecedor logado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Depósitos retornados"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping()
    public ResponseEntity<List<Warehouse>> getAllWarehouses(@Parameter(hidden = true) @RequestHeader String authorization) {
        long supplierId = Long.parseLong(jwtUtils.getClaim(authorization, "supplier_id").toString());

        List<Warehouse> response = warehouseService.getWarehouses(supplierId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(description = "Cria um novo depósito associado ao fornecedor logado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Depósito criado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(schema = @Schema(hidden = true)))
    })
    @PostMapping()
    public ResponseEntity<Warehouse> createWarehouse(@Parameter(hidden = true) @RequestHeader String authorization,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Informações do depósito") @RequestBody Warehouse body) {
        long supplierId = Long.parseLong(jwtUtils.getClaim(authorization, "supplier_id").toString());

        Warehouse response = warehouseService.createWarehouse(body, supplierId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(description = "Atualiza o depósito informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Depósito criado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", description = "Depósito não encontrado", content = @Content(schema = @Schema(hidden = true)))
    })
    @PutMapping("{warehouseId}")
    public ResponseEntity<Warehouse> updateWarehouse(@Parameter(hidden = true) @RequestHeader String authorization,
            @Parameter(in = ParameterIn.PATH, description = "ID do depósito") @PathVariable long warehouseId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody @RequestBody Warehouse body) {
        long supplierId = Long.parseLong(jwtUtils.getClaim(authorization, "supplier_id").toString());

        Warehouse response = warehouseService.updateWarehouse(warehouseId, body, supplierId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(description = "Apaga o depósito informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Depósito apagado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", description = "Depósito não encontrado", content = @Content(schema = @Schema(hidden = true)))
    })
    @DeleteMapping("{warehouseId}")
    public ResponseEntity<DeleteResponseDTO> deleteWarehouse(
            @Parameter(in = ParameterIn.PATH, description = "ID do depósito") @PathVariable long warehouseId) {
        warehouseService.deleteWarehouse(warehouseId);

        return new ResponseEntity<>(
                DeleteResponseDTO.builder().status("deleted").build(),
                HttpStatus.OK);
    }
}
