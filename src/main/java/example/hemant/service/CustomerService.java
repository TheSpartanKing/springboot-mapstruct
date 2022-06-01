package example.hemant.service;

import example.hemant.dto.CustomerDto;
import example.hemant.dto.mapper.CustomerMapper;
import example.hemant.entity.Customer;
import example.hemant.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerMapper customerMapper;

    private final CustomerRepository customerRepository;


    public CustomerDto getCustomer(Long id) {
        return customerRepository.findById(id).map(customerMapper::customerToCustomerDTO)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public CustomerDto saveCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDto);
        Customer save = customerRepository.save(customer);
        return Optional.of(save)
                .map(customerMapper::customerToCustomerDTO)
                .orElseThrow(() -> new RuntimeException("Customer not saved"));
    }

    public void deleteCustomer(Long id) {
        customerRepository.findById(id).ifPresentOrElse(customerRepository::delete,
                () -> { throw new RuntimeException("Customer not found: "+id); });
    }

    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll().stream().map(customerMapper::customerToCustomerDTO)
                .collect(Collectors.toList());
    }
}
