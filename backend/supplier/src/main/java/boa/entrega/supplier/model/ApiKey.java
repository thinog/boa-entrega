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

    @JsonIgnore
    private long supplierId;

    @JsonIgnore
    @Column(name = "external_id")
    private String externalId;

    private String name;

    @JsonIgnore
    private String hash;

    @Enumerated(EnumType.STRING)
    @Column(name = "access_type")
    private AccessType accessType;

    @Column(name = "created_at", insertable = false, nullable = false, updatable = false)
    private String createdAt;

    @JsonIgnore
    @Column(insertable = false)
    private boolean active;

    public enum AccessType {
        READONLY,
        FULL_ACCESS
    }
}
