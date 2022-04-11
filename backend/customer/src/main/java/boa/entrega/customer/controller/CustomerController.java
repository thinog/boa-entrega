package boa.entrega.customer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @GetMapping("{id}")
    public ResponseEntity<Object> getCustomer(@PathVariable long id) {
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
