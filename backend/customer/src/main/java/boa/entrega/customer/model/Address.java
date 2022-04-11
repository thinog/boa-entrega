package boa.entrega.customer.model;

import lombok.Data;

@Data
public class Address {
    private long id;

    private Customer customer;

    private String street;

    private String number;

    private String complement;

    private String neighborhood;

    private String city;

    private String state;

    private String reference;

    private String deliveryInstructions;
}
