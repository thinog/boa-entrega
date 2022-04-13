package boa.entrega.supplier.repository;

import boa.entrega.supplier.model.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {

    @Query("from ApiKey where supplierId = :supplierId and active = true")
    List<ApiKey> getAllBySupplierId(long supplierId);
}
