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
import java.util.Map;

@RestController
@RequestMapping("customer")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable long id) {
        Customer response = customerService.getCustomer(id);
        return new ResponseEntity<Customer>(response, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer response = customerService.saveCustomer(customer);
        return new ResponseEntity<Customer>(response, HttpStatus.CREATED);
    }

    @PutMapping("address/{customerId}")
    public ResponseEntity<List<Address>> updateAddresses(
            @PathVariable long customerId, @RequestBody List<Address> addresses) {
        List<Address> response = customerService.updateAddresses(customerId, addresses);
        return new ResponseEntity<List<Address>>(response, HttpStatus.OK);
    }
}
