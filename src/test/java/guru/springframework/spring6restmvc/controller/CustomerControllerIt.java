package guru.springframework.spring6restmvc.controller;

import guru.springframework.spring6restmvc.entities.Customer;
import guru.springframework.spring6restmvc.mappers.CustomerMapper;
import guru.springframework.spring6restmvc.model.CustomerDTO;
import guru.springframework.spring6restmvc.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CustomerControllerIt {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerController customerController;
    @Autowired
    private CustomerMapper customerMapper;

    @Test
    @Rollback
    @Transactional
    void testPatchCustomer() {
        Customer customer = customerRepository.findAll().getFirst();
        CustomerDTO customerDTO = customerMapper.customerToCustomerDto(customer);
        customerDTO.setId(null);
        customerDTO.setVersion(0);
        final String customerName = "Berbatec";
        customerDTO.setCustomerName(customerName);
        ResponseEntity<?> responseEntity = customerController.patchCustomer(customer.getId(), customerDTO);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.valueOf(204));
        customerRepository.findById(customer.getId())
                .ifPresent(updatedCustomer -> assertThat(updatedCustomer.getCustomerName()).isEqualTo(customerName));

    }

    @Test
    void testDeleteByIdNotFound() {
        Customer customer = customerRepository.findAll().getFirst();
        ResponseEntity<Void> responseEntity = customerController.deleteById(customer.getId());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.valueOf(204));
        assertThat(customerRepository.findById(customer.getId())).isEmpty();
    }

    @Test
    @Rollback
    @Transactional
    void testDeleteById() {
        Customer customer = customerRepository.findAll().getFirst();
        ResponseEntity<Void> responseEntity = customerController.deleteById(customer.getId());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.valueOf(204));
        assertThat(customerRepository.findById(customer.getId())).isEmpty();
    }

    @Test
    void testUpdateExistingCustomerNotFound() {
        assertThrows(NotFoundException.class, () -> {
            customerController.updateCustomer(UUID.randomUUID(), CustomerDTO.builder().build());
        });

    }

    @Test
    void testUpdateExistingCustomer() {
        Customer customer = customerRepository.findAll().getFirst();
        CustomerDTO customerDTO = customerMapper.customerToCustomerDto(customer);
        customerDTO.setId(null);
        customerDTO.setVersion(0);
        final String customerName = "Dobche";
        customerDTO.setCustomerName(customerName);
        ResponseEntity responseEntity = customerController.updateCustomer(customer.getId(), customerDTO);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.valueOf(204));
        Customer updatedCustomer = customerRepository.findById(customer.getId()).get();
        assertThat(updatedCustomer.getCustomerName()).isEqualTo(customerName);
    }

    @Transactional
    @Rollback
    @Test
    void testSaveNewCustomer() {
        CustomerDTO customerDTO = CustomerDTO.builder()
                .customerName("dopeto90")
                .build();
        ResponseEntity responseEntity = customerController.saveCustomer(customerDTO);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.valueOf(201));
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();
        String[] locationUUID = responseEntity.getHeaders().getLocation().getPath().split("/");
        UUID savedUUID = UUID.fromString(locationUUID[4]);
        Customer customer = customerRepository.findById(savedUUID).get();
        assertThat(customer).isNotNull();
    }

    @Rollback
    @Transactional
    @Test
    void testListAllEmptyList() {
        customerRepository.deleteAll();
        List<CustomerDTO> dtos = customerController.customerList();
        assertThat(dtos).isEmpty();
    }

    @Test
    void testListAllWithCustomers() {
        List<CustomerDTO> dtos = customerController.customerList();
        assertThat(dtos.size()).isEqualTo(2);
    }

    @Test
    void testGetByIdNotFound() {
        assertThrows(NotFoundException.class, () -> {
            customerController.getCustomerById(UUID.randomUUID());
        });
    }

    @Test
    void testGetCustomerById() {
        Customer customer = customerRepository.findAll().getFirst();
        CustomerDTO customerDTO = customerController.getCustomerById(customer.getId());
        assertThat(customerDTO.getId()).isNotNull();
    }
}