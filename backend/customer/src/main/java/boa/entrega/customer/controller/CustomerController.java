package boa.entrega.customer.controller;

import boa.entrega.customer.model.Address;
import boa.entrega.customer.model.Customer;
import boa.entrega.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable long id) {
        Customer response = customerService.getCustomer(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer body) {
        Customer response = customerService.saveCustomer(body);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("address/{customerId}")
    public ResponseEntity<List<Address>> updateAddresses(
            @PathVariable long customerId, @RequestBody List<Address> body) {
        List<Address> response = customerService.updateAddresses(customerId, body);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
