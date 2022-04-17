package boa.entrega.supplier.controller;

import boa.entrega.supplier.controller.dto.DeleteResponseDTO;
import boa.entrega.supplier.model.Warehouse;
import boa.entrega.supplier.service.WarehouseService;
import boa.entrega.supplier.utils.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

    @Operation(description = "Gets all warehouses of the logged supplier")
    @GetMapping()
    public ResponseEntity<List<Warehouse>> getAllWarehouses(@Parameter(hidden = true) @RequestHeader String authorization) {
        long supplierId = Long.parseLong(jwtUtils.getClaim(authorization, "supplier_id").toString());

        List<Warehouse> response = warehouseService.getWarehouses(supplierId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(description = "Creates a new warehouse for the logged supplier")
    @PostMapping()
    public ResponseEntity<Warehouse> createWarehouse(@Parameter(hidden = true) @RequestHeader String authorization,
            @io.swagger.v3.oas.annotations.parameters.RequestBody @RequestBody Warehouse body) {
        long supplierId = Long.parseLong(jwtUtils.getClaim(authorization, "supplier_id").toString());

        Warehouse response = warehouseService.createWarehouse(body, supplierId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(description = "Updates a warehouse")
    @PutMapping("{warehouseId}")
    public ResponseEntity<Warehouse> updateWarehouse(@Parameter(hidden = true) @RequestHeader String authorization,
            @PathVariable long warehouseId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody @RequestBody Warehouse body) {
        long supplierId = Long.parseLong(jwtUtils.getClaim(authorization, "supplier_id").toString());

        Warehouse response = warehouseService.updateWarehouse(warehouseId, body, supplierId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(description = "Removes a warehouse")
    @DeleteMapping("{warehouseId}")
    public ResponseEntity<DeleteResponseDTO> deleteWarehouse(@PathVariable long warehouseId) {
        warehouseService.deleteWarehouse(warehouseId);

        return new ResponseEntity<>(
                DeleteResponseDTO.builder().status("deleted").build(),
                HttpStatus.OK);
    }
}
