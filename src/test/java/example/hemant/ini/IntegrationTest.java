package example.hemant.ini;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import example.hemant.dto.CustomerDto;
import example.hemant.entity.Address;
import example.hemant.entity.Customer;
import example.hemant.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setup() {
        Address address = Address.builder()
                .city("Pune")
                .zip("12334")
                .street("Pune")
                .state("Maharashtra")
                .build();
        Customer customer = Customer.builder()
                .firstName("sonu").LastName("sahu")
                .address(address)
                .build();
        Customer saveCustomer = customerRepository.save(customer);
        System.out.println(saveCustomer);
    }

    @Test
    public void testGetCustomer() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/api/v1/customers");
        MvcResult mvcResult = mockMvc.perform(builder).andReturn();
        String asString = mvcResult.getResponse().getContentAsString();
        List<CustomerDto> customerDtos = fromJson(asString, new TypeReference<List<CustomerDto>>() {
        });
        System.out.println(customerDtos);
    }

    @Test
    public void testCreateCustomer() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/api/v1/customer/{id}", 1);
        MvcResult mvcResult = mockMvc.perform(builder).andReturn();
        String asString = mvcResult.getResponse().getContentAsString();
        CustomerDto customerDto = fromJson(asString, CustomerDto.class);
        System.out.println(customerDto);
    }

    private static <T> T fromJson(String json, Class<T> classz) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, classz);
    }

    private static <T> T fromJson(String json, TypeReference<T> TypeReference) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, TypeReference);
    }
}
