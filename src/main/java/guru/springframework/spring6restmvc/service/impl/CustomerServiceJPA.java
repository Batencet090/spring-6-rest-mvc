package guru.springframework.spring6restmvc.service.impl;

import guru.springframework.spring6restmvc.mappers.CustomerMapper;
import guru.springframework.spring6restmvc.model.CustomerDTO;
import guru.springframework.spring6restmvc.repositories.CustomerRepository;
import guru.springframework.spring6restmvc.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Primary
@RequiredArgsConstructor
public class CustomerServiceJPA implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerDTO> findAll() {
        return customerRepository.findAll()
                .stream().map(customerMapper::customerToCustomerDto).toList();
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID id) {
        return Optional.ofNullable(customerMapper.customerToCustomerDto(customerRepository
                .findById(id)
                .orElse(null)));
    }

    @Override
    public CustomerDTO save(CustomerDTO customer) {
        return customerMapper.customerToCustomerDto(customerRepository
                .save(customerMapper.customerDtoToCustomer(customer)));
    }

    @Override
    public Optional<CustomerDTO> updateById(UUID id, CustomerDTO customer) {
        AtomicReference<CustomerDTO> atomicReference = new AtomicReference<>();
        customerRepository.findById(id).ifPresent(foundCustomer -> {
            foundCustomer.setCustomerName(customer.getCustomerName());
            foundCustomer.setCreatedDate(customer.getCreatedDate());
            foundCustomer.setLastModifiedDate(customer.getLastModifiedDate());
            atomicReference.set(customerMapper.customerToCustomerDto(customerRepository.save(foundCustomer)));
        });

        return Optional.ofNullable(atomicReference.get());
    }

    @Override
    public Boolean deleteById(UUID id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<CustomerDTO> patchById(UUID id, CustomerDTO customer) {
        AtomicReference<CustomerDTO> atomicReference = new AtomicReference<>();

        customerRepository.findById(id).ifPresent(foundCustomer -> {
            if (customer.getCustomerName() != null) {
                foundCustomer.setCustomerName(customer.getCustomerName());
            }
            atomicReference.set(customerMapper.customerToCustomerDto(customerRepository.save(foundCustomer)));
        });

        return Optional.ofNullable(atomicReference.get());
    }
}
