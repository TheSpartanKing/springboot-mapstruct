package example.hemant;

import example.hemant.dto.AddressDto;
import example.hemant.dto.CustomerDto;
import example.hemant.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringbokDtoTutorialApplication implements CommandLineRunner {

    private final CustomerService customerService;

    public static void main(String[] args) {
        SpringApplication.run(SpringbokDtoTutorialApplication.class, args);
    }

    @Override
    public void run(String... args)  {
        AddressDto addressDto = AddressDto.builder()
                .city("Pune")
                .zip("12334")
                .street("Pune")
                .state("Maharashtra")
                .build();
        CustomerDto customerDto = CustomerDto.builder()
                .firstName("Hemant")
                .lastName("Kumar")
                .customerAddress(addressDto)
                .build();
        CustomerDto saveCustomer = customerService.saveCustomer(customerDto);
        System.out.println(saveCustomer);
    }
}
