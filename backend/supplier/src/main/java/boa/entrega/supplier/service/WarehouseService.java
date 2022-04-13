package boa.entrega.supplier.service;

import boa.entrega.supplier.exception.EntityNotFoundException;
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

    public List<Warehouse> getWarehouses(long supplierId) {
        return warehouseRepository.getAllBySupplierId(supplierId);
    }

    public Warehouse createWarehouse(Warehouse warehouse, long supplierId) {
        warehouse.setSupplierId(supplierId);
        return warehouseRepository.save(warehouse);
    }

    public Warehouse updateWarehouse(Warehouse warehouse, long supplierId) {
        warehouse.setSupplierId(supplierId);
        return warehouseRepository.save(warehouse);
    }

    public void deleteWarehouse(long warehouseId) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new EntityNotFoundException("warehouse"));
        warehouse.setActive(false);
        warehouseRepository.save(warehouse);
    }
}
