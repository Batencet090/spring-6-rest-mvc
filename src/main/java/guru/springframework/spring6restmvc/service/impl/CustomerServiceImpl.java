package guru.springframework.spring6restmvc.service.impl;

import guru.springframework.spring6restmvc.model.CustomerDTO;
import guru.springframework.spring6restmvc.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
    private final Map<UUID, CustomerDTO> customers;

    public CustomerServiceImpl() {
        this.customers = new HashMap<>();
        CustomerDTO customer1 = CustomerDTO.builder()
                .customerName("Ivan Dobkov")
                .id(UUID.randomUUID())
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
        CustomerDTO customer2 = CustomerDTO.builder()
                .customerName("Simona Dobkova")
                .id(UUID.randomUUID())
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
        CustomerDTO customer3 = CustomerDTO.builder()
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
    public List<CustomerDTO> findAll() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public CustomerDTO getCustomerById(UUID id) {
        log.debug("Getting customer with id in service: {}", id.toString());
        return customers.get(id);
    }

    @Override
    public CustomerDTO save(CustomerDTO customer) {
        CustomerDTO savedCustomer = CustomerDTO.builder()
                .customerName(customer.getCustomerName())
                .id(UUID.randomUUID())
                .version(customer.getVersion())
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
        customers.put(savedCustomer.getId(), savedCustomer);
        return savedCustomer;
    }

    @Override
    public void updateById(UUID id, CustomerDTO customer) {
        CustomerDTO updatedCustomer = customers.get(id);
        updatedCustomer.setCustomerName(customer.getCustomerName());
        customers.put(updatedCustomer.getId(), updatedCustomer);
    }

    @Override
    public void deleteById(UUID id) {
        customers.remove(id);
    }

    @Override
    public void patchById(UUID id, CustomerDTO customer) {
        CustomerDTO patchedCustomer = customers.get(id);
        if (customer.getCustomerName()!= null) {
            patchedCustomer.setCustomerName(customer.getCustomerName());
        }
        customers.put(patchedCustomer.getId(), patchedCustomer);
    }
}
