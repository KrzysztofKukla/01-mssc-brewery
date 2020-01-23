package pl.krzys.kukla.msscbrewery.service;

import org.springframework.stereotype.Service;
import pl.krzys.kukla.msscbrewery.web.model.CustomerDto;

import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
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

}
