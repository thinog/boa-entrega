package boa.entrega.customer.model;

import lombok.Data;

import java.util.List;

@Data
public class Customer {
    private long id;

    private List<Address> addresses;
}
