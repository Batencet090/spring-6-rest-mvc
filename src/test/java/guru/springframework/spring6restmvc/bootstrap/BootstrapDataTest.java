package guru.springframework.spring6restmvc.bootstrap;

import guru.springframework.spring6restmvc.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BootstrapDataTest {
    @Autowired
    CustomerRepository customerRepository;
    BootstrapData bootstrapData;

    @BeforeEach
    void setUp() {
        bootstrapData = new BootstrapData(customerRepository);
    }

    @Test
    void testRun() throws Exception {
        bootstrapData.run(null);
        assertThat(customerRepository.count()).isEqualTo(3);
    }
}