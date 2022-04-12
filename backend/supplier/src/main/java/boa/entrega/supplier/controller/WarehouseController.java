package boa.entrega.supplier.controller;

import boa.entrega.supplier.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WarehouseController {

    private final WarehouseService warehouseService;
}
