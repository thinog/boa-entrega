package boa.entrega.supplier.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnore
    private long supplierId;

    private String name;

    private String street;

    private String number;

    private String complement;

    private String neighborhood;

    private String city;

    private String state;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "open_hours")
    private String openHours;

    @JsonIgnore
    @Column(name = "created_at", insertable = false, nullable = false, updatable = false)
    private String createdAt;

    @JsonIgnore
    @Column(insertable = false)
    private boolean active;
}
