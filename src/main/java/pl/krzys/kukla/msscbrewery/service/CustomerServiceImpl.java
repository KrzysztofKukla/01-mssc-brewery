package pl.krzys.kukla.msscbrewery.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.krzys.kukla.msscbrewery.web.model.CustomerDto;

import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public CustomerDto getById(UUID uuid) {
        CustomerDto customerDto = CustomerDto.builder()
            .uuid(UUID.randomUUID())
            .name("first Customer")
            .build();
        return customerDto;
    }

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        return CustomerDto.builder()
            .uuid(UUID.randomUUID())
            .name(customerDto.getName())
            .build();
    }

    @Override
    public void updateCustomer(UUID customerId, CustomerDto customerDto) {
        log.debug("Updating Customer...");
        //TODO
    }

    @Override
    public void deleteCustomerById(UUID customerId) {
        log.debug("Deleting Customer...");
        //TODO
    }

}
