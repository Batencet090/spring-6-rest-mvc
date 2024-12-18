package guru.springframework.spring6restmvc.service;

import guru.springframework.spring6restmvc.model.CustomerDTO;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    List<CustomerDTO> findAll();

    CustomerDTO getCustomerById(UUID id);

    CustomerDTO save(CustomerDTO customer);

    void updateById(UUID id , CustomerDTO customer);

    void deleteById(UUID id);

    void patchById(UUID id, CustomerDTO customer);
}
