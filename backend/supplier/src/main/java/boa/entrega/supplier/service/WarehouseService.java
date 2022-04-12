package boa.entrega.supplier.service;

import boa.entrega.supplier.model.Warehouse;
import boa.entrega.supplier.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;
}
