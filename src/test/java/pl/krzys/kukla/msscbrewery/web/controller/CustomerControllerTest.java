package pl.krzys.kukla.msscbrewery.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.krzys.kukla.msscbrewery.service.CustomerService;
import pl.krzys.kukla.msscbrewery.web.model.CustomerDto;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Krzysztof Kukla
 */
@WebMvcTest(controllers = CustomerController.class)
class CustomerControllerTest {

    @MockBean
    private CustomerService customerService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private CustomerDto validCustomerDto;

    @BeforeEach
    void setUp() {
        validCustomerDto = CustomerDto.builder()
            .uuid(UUID.randomUUID())
            .name("first Customer name")
            .build();
    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customer/{beerId}", UUID.randomUUID())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        BDDMockito.then(customerService).should().getById(any(UUID.class));
    }

    @Test
    void handlePost() throws Exception {
        CustomerDto customerDto = validCustomerDto;
        customerDto.setUuid(null);
        CustomerDto savedCustomerDto = CustomerDto.builder().uuid(UUID.randomUUID()).name("saved CustomerDto").build();
        String customerJson = objectMapper.writeValueAsString(customerDto);
        BDDMockito.given(customerService.saveCustomer(any(CustomerDto.class))).willReturn(savedCustomerDto);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customer")
            .contentType(MediaType.APPLICATION_JSON)
            .content(customerJson))
            .andExpect(status().isCreated())
            .andReturn();
        String locationHeader = mvcResult.getResponse().getHeader("location");

        Assertions.assertEquals("/api/v1/customer" + savedCustomerDto.getUuid(), locationHeader);
        BDDMockito.then(customerService).should().saveCustomer(any(CustomerDto.class));

    }

}