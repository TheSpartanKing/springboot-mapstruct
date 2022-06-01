package example.hemant.dto.mapper;

import example.hemant.dto.CustomerDto;
import example.hemant.entity.Customer;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper CUSTOMER_MAPPER = Mappers.getMapper(CustomerMapper.class);
    @Mapping(target = "customerAddress", source = "address")
    CustomerDto customerToCustomerDTO(Customer customer);
    @InheritInverseConfiguration
    Customer customerDTOToCustomer(CustomerDto customerDTO);
    List<CustomerDto> customersToCustomerDTOs(List<Customer> customers);
    List<Customer> customerDTOsToCustomers(List<CustomerDto> customerDTOs);
}
