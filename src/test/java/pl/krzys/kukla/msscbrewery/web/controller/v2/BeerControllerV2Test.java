package pl.krzys.kukla.msscbrewery.web.controller.v2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.krzys.kukla.msscbrewery.service.v2.BeerServiceV2;
import pl.krzys.kukla.msscbrewery.web.model.v2.BeerDtoV2;
import pl.krzys.kukla.msscbrewery.web.model.v2.BeerStyleEnum;

import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
@WebMvcTest(controllers = BeerControllerV2.class)
class BeerControllerV2Test {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BeerServiceV2 beerServiceV2;

    private BeerDtoV2 validBeerDto;

    @BeforeEach
    void setUp() {
        validBeerDto = BeerDtoV2.builder()
            .beerName("beerName version2")
            .beerStyle(BeerStyleEnum.LAGER)
            .upc(2222L)
            .build();

    }

    @Test
    void getBeer() throws Exception {
        BDDMockito.given(beerServiceV2.getBeerById(ArgumentMatchers.any(UUID.class))).willReturn(validBeerDto);

        MvcResult mvcResult = mockMvc.perform(RestDocumentationRequestBuilders.get("/api/v2/beer/{beerId}", UUID.randomUUID())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcRestDocumentation.document("v2/beer-get",
                RequestDocumentation.pathParameters(
                    RequestDocumentation.parameterWithName("beerId").description("id for beer")
                )
                )
            ).andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        System.out.println(contentAsString);
    }

    @Test
    void handlePost() throws Exception {
    }

    @Test
    void handleUpdate() {
    }

    @Test
    void deleteBeer() {
    }

}