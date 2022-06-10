package example.hemant.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import example.hemant.dto.CustomerDto;
import example.hemant.entity.Customer;
import example.hemant.mock.MockData;
import example.hemant.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static example.hemant.dto.mapper.CustomerMapper.CUSTOMER_MAPPER;
import static example.hemant.mock.MockData.getAddress;
import static example.hemant.mock.Transform.fromJson;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CustomerControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private CustomerController customerController;
    @Mock
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    void getCustomer() throws Exception {
        Customer customer = MockData.getCustomer(getAddress());
        when(customerService.getAllCustomers()).thenReturn(List.of(CUSTOMER_MAPPER.customerToCustomerDTO(customer)));
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/api/v1/customers");
        MvcResult mvcResult = mockMvc.perform(builder).andReturn();
        String asString = mvcResult.getResponse().getContentAsString();
        List<CustomerDto> customerDtoList = fromJson(asString, new TypeReference<>() {
        });
        System.out.println("Result:" + customerDtoList);
    }


    @Test
    void createCustomer() {
    }

    @Test
    void getCustomerById() throws Exception {
        when(customerService.getCustomer(anyLong())).
                thenReturn(CUSTOMER_MAPPER.customerToCustomerDTO(MockData.getCustomer(getAddress())));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/customer/{id}", 1);
        String asString = mockMvc.perform(requestBuilder).andReturn().getResponse().getContentAsString();
        System.out.println("Result:" + asString);
    }

    @Test
    void deleteCustomerById() {
    }


}