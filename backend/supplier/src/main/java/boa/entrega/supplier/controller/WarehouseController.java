package boa.entrega.supplier.controller;

import boa.entrega.supplier.controller.dto.DeleteResponseDTO;
import boa.entrega.supplier.model.Warehouse;
import boa.entrega.supplier.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("warehouse")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WarehouseController {

    private final WarehouseService warehouseService;

    @GetMapping("all")
    public ResponseEntity<List<Warehouse>> getApiKeys() {
        List<Warehouse> response = warehouseService.getWarehouses(0);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Warehouse> createCustomer(@RequestBody Warehouse body) {
        Warehouse response = warehouseService.createWarehouse(body, 0);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("{warehouseId}")
    public ResponseEntity<DeleteResponseDTO> deleteApiKey(@RequestParam long warehouseId) {
        warehouseService.deleteWarehouse(warehouseId);

        return new ResponseEntity<>(
                DeleteResponseDTO.builder().status("deleted").build(),
                HttpStatus.OK);
    }
}
