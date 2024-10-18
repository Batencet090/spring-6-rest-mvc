package guru.springframework.spring6restmvc.service;

import guru.springframework.spring6restmvc.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    List<Customer> findAll();

    Customer getCustomerById(UUID id);

    Customer save(Customer customer);
}
