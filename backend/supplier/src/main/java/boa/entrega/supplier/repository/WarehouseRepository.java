package boa.entrega.supplier.repository;

import boa.entrega.supplier.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    @Query("from Warehouse where supplierId = :supplierId and active = true")
    List<Warehouse> getAllBySupplierId(long supplierId);
}
