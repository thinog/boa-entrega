package boa.entrega.supplier.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ApiKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long supplierId;

    private String name;

    @JsonIgnore
    private String hash;

    private AccessType type;

    @Column(name = "created_at")
    private String createdAt;

    @JsonIgnore
    private boolean active;

    public enum AccessType {
        READONLY,
        FULL_ACCESS
    }
}
