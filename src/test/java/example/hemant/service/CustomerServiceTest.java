package example.hemant.service;

import example.hemant.mock.MockData;
import example.hemant.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static example.hemant.mock.MockData.getAddress;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    void getAllCustomers() {
        final var customer = MockData.getCustomer(getAddress());
        when(customerRepository.findAll())
                .thenReturn(List.of(customer));
        final var allCustomers = customerService.getAllCustomers();
        System.out.println(allCustomers);

    }

    @Test
    void saveCustomer() {
    }

    @Test
    void deleteCustomer() {
    }

    @Test
    void getCustomer() {
        when(customerRepository.findById(1L))
                .thenReturn(Optional.ofNullable(MockData.getCustomer(getAddress())));
        var customer = customerService.getCustomer(1L);
        System.out.println(customer);
    }
}