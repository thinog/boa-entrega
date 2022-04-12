package boa.entrega.supplier.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ApiKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @JsonIgnore
    private String hash;

    @JsonIgnore
    private String salt;

    private Type type;

    @Column(name = "created_at")
    private String createdAt;

    @JsonIgnore
    private boolean active;

    public enum Type {
        READONLY,
        FULL_ACCESS
    }
}
