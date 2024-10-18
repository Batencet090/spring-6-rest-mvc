package guru.springframework.spring6restmvc.service.impl;

import guru.springframework.spring6restmvc.model.Customer;
import guru.springframework.spring6restmvc.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
    private Map<UUID, Customer> customers;

    public CustomerServiceImpl() {
        this.customers = new HashMap<>();
        Customer customer1 = Customer.builder()
                .customerName("Ivan Dobkov")
                .id(UUID.randomUUID())
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
        Customer customer2 = Customer.builder()
                .customerName("Simona Dobkova")
                .id(UUID.randomUUID())
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
        Customer customer3 = Customer.builder()
                .customerName("Diana Dobkova")
                .id(UUID.randomUUID())
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
        customers.put(customer1.getId(), customer1);
        customers.put(customer2.getId(), customer2);
        customers.put(customer3.getId(), customer3);
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public Customer getCustomerById(UUID id) {
        log.debug("Getting customer with id in service: {}", id.toString());
        return customers.get(id);
    }

    @Override
    public Customer save(Customer customer) {
        Customer savedCustomer = Customer.builder()
                .customerName(customer.getCustomerName())
                .id(UUID.randomUUID())
                .version(customer.getVersion())
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
        customers.put(savedCustomer.getId(), savedCustomer);
        return savedCustomer;
    }
}
