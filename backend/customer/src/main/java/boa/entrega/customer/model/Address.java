package boa.entrega.customer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="customer_id", nullable = true, insertable = false, updatable = false)
    @JsonIgnore
    private long customerId;

    private String street;

    private String number;

    private String complement;

    private String neighborhood;

    private String city;

    private String state;

    @Column(name = "zip_code")
    private String zipCode;

    private String reference;

    @Column(name = "delivery_instructions")
    private String deliveryInstructions;

    @Column(name = "created_at", insertable = false, nullable = false, updatable = false)
    private String createdAt;

    @JsonIgnore
    @Column(insertable = false)
    private boolean active;
}
