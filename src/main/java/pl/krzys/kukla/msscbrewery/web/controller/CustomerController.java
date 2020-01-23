package pl.krzys.kukla.msscbrewery.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.krzys.kukla.msscbrewery.service.CustomerService;
import pl.krzys.kukla.msscbrewery.web.model.CustomerDto;

import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
@RequestMapping("/api/v1/customer")
@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getById(@PathVariable UUID customerId) {
        return new ResponseEntity<>(customerService.getById(customerId), HttpStatus.OK);
    }

}
