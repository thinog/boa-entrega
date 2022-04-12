package boa.entrega.customer.service;

import boa.entrega.customer.exception.EntityNotFoundException;
import boa.entrega.customer.model.Address;
import boa.entrega.customer.model.Customer;
import boa.entrega.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer getCustomer(long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("customer"));
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Address> updateAddresses(long customerId, List<Address> addresses) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("customer"));

        customer.setAddresses(addresses);

        return customerRepository.save(customer).getAddresses();
    }
}
