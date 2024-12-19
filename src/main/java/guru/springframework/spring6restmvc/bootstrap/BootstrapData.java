package guru.springframework.spring6restmvc.bootstrap;

import guru.springframework.spring6restmvc.entities.Customer;
import guru.springframework.spring6restmvc.model.CustomerDTO;
import guru.springframework.spring6restmvc.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;
@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {
    private final CustomerRepository customerRepository;
    @Override
    public void run(String... args) throws Exception {
        loadCustomerData();
    }

    private void loadCustomerData() {
        if(customerRepository.count() == 0) {
            Customer customer1 = Customer.builder()
                    .customerName("Ivan Dobkov")
                    .version(1)
                    .createdDate(LocalDateTime.now())
                    .lastModifiedDate(LocalDateTime.now())
                    .build();
            Customer customer2 = Customer.builder()
                    .customerName("Simona Dobkova")
                    .version(1)
                    .createdDate(LocalDateTime.now())
                    .lastModifiedDate(LocalDateTime.now())
                    .build();
            Customer customer3 = Customer.builder()
                    .customerName("Diana Dobkova")
                    .version(1)
                    .createdDate(LocalDateTime.now())
                    .lastModifiedDate(LocalDateTime.now())
                    .build();
            customerRepository.saveAll(Arrays.asList(customer1, customer2, customer3));
        }
    }
}
