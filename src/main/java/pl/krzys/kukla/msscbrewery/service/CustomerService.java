package pl.krzys.kukla.msscbrewery.service;

import pl.krzys.kukla.msscbrewery.web.model.CustomerDto;

import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
public interface CustomerService {
    CustomerDto getById(UUID uuid);

    CustomerDto saveCustomer(CustomerDto customerDto);

    void updateCustomer(UUID customerId, CustomerDto customerDto);

    void deleteCustomerById(UUID customerId);

}
