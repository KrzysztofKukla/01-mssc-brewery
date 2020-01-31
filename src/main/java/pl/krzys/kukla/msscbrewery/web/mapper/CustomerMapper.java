package pl.krzys.kukla.msscbrewery.web.mapper;

import org.mapstruct.Mapper;
import pl.krzys.kukla.msscbrewery.domain.Customer;
import pl.krzys.kukla.msscbrewery.web.model.CustomerDto;

/**
 * @author Krzysztof Kukla
 */
@Mapper
public interface CustomerMapper {
    Customer customerDtoToCustomer(CustomerDto customerDto);

    CustomerDto customerToCustomerDto(Customer customer);

}
