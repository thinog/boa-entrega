package boa.entrega.customer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(unique = true)
    private String email;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    private List<Address> addresses;

    @Column(name = "created_at", insertable = false, nullable = false, updatable = false)
    private String createdAt;

    @JsonIgnore
    @Column(insertable = false)
    private boolean active;
}
