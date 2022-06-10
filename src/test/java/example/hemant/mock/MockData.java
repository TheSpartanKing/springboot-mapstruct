package example.hemant.mock;

import example.hemant.entity.Address;
import example.hemant.entity.Customer;

public class MockData {
    public static Customer getCustomer(Address address) {
        return Customer.builder()
                .firstName("sonu").lastName("sahu")
                .address(address)
                .build();
    }

    public static Address getAddress() {
        return Address.builder()
                .city("Pune")
                .zip("12334")
                .street("Pune")
                .state("Maharashtra")
                .build();
    }
}
