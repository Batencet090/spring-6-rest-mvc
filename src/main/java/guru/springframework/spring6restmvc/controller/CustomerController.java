package guru.springframework.spring6restmvc.controller;

import guru.springframework.spring6restmvc.model.Customer;
import guru.springframework.spring6restmvc.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public List<Customer> customerList() {
        return customerService.findAll();
    }
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable("id") UUID id) {
        log.debug("Get customer by Id in the controller");
        return customerService.getCustomerById(id);
    }
}
