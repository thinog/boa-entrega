package boa.entrega.customer.service;

import boa.entrega.customer.exception.EntityNotFoundException;
import boa.entrega.customer.messaging.publish.MessagePublisher;
import boa.entrega.customer.model.Address;
import boa.entrega.customer.model.Customer;
import boa.entrega.customer.model.Event;
import boa.entrega.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final MessagePublisher messagePublisher;

    public Customer getCustomer(long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("customer"));
    }

    public Customer saveCustomer(Customer customer) {
        Customer createdCustomer = customerRepository.save(customer);

        messagePublisher.publish(Event.builder().eventType("CUSTOMER_CREATED").body(createdCustomer).build(), "queue-url");

        return createdCustomer;
    }

    public List<Address> updateAddresses(long customerId, List<Address> addresses) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("customer"));

        customer.setAddresses(addresses);

        List<Address> adressesUpdated = customerRepository.save(customer).getAddresses();

        messagePublisher.publish(Event.builder().eventType("CUSTOMER_ADRESSES_CREATED").body(adressesUpdated).build(), "queue-url");

        return adressesUpdated;
    }
}
