package pl.krzys.kukla.msscbrewery.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.krzys.kukla.msscbrewery.service.BeerService;
import pl.krzys.kukla.msscbrewery.web.model.BeerDto;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Krzysztof Kukla
 */
//autoconfiguration REST docs for us
@ExtendWith(value = RestDocumentationExtension.class) //we need to add SpringExtension as well, but this is done int @WebMvcTest
//here we enable autoconfigue and change the default configuration for @AutoConfigureRestDocs
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "dev.someserver", uriPort = 80)
@WebMvcTest(controllers = BeerController.class)
@ComponentScan(value = "pl.krzys.kukla.msscbrewery.web.mapper")
class BeerControllerTest {

    @MockBean
    private BeerService beerService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private BeerDto validBeerDto;

    @BeforeEach
    void setUp() {
        validBeerDto = BeerDto.builder()
            .uuid(UUID.randomUUID())
            .beerName("first Beer")
            .beerStyle("First Style")
            .upc(1234567L)
            .build();
    }

    @Test
    void getBeer() throws Exception {
        BDDMockito.when(beerService.getBeerById(any(UUID.class))).thenReturn(validBeerDto);

        MvcResult mvcResult = mockMvc.perform(RestDocumentationRequestBuilders.get("/api/v1/beer/{beerId}", validBeerDto.getUuid())
            .accept(MediaType.APPLICATION_JSON)
            .param("iscold", "yes")
        )
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.upc", Matchers.is(validBeerDto.getUpc().intValue())))
            .andExpect(jsonPath("$.beerStyle", Matchers.equalTo(validBeerDto.getBeerStyle())))

            //andDo allows to generate documentation with pathParameters
            .andDo(MockMvcRestDocumentation.document("/v1/beer",
                RequestDocumentation.pathParameters(
                    RequestDocumentation.parameterWithName("beerId").description("UUID of desired beer to get")
                ),
                RequestDocumentation.requestParameters(
                    RequestDocumentation.parameterWithName("iscold").description("Is beer cold query param?")
                ),
                //here we need to document all fields for BeerDto, otherwise test will fail
                PayloadDocumentation.responseFields(
                    PayloadDocumentation.fieldWithPath("uuid").description("UUID of beer"),
                    PayloadDocumentation.fieldWithPath("beerName").description("beerName of beer"),
                    PayloadDocumentation.fieldWithPath("beerStyle").description("beerStyle of beer"),
                    PayloadDocumentation.fieldWithPath("upc").description("upc of beer"),
                    PayloadDocumentation.fieldWithPath("createdDate").description("createdDate of beer"),
                    PayloadDocumentation.fieldWithPath("lastUpdatedDate").description("lastUpdatedDate of beer")
                )
            ))
            .andReturn();

        String beerDtoJson = mvcResult.getResponse().getContentAsString();
        System.out.println(beerDtoJson);
        Assertions.assertThat(mvcResult.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    void handlePost() throws Exception {
        BeerDto beerDto = validBeerDto;
        beerDto.setUuid(null);
        BeerDto savedBeerDto = BeerDto.builder().uuid(UUID.randomUUID()).beerName("second Beer").beerStyle("second Style").upc(5678L).build();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);
        BDDMockito.given(beerService.saveBeer(any(BeerDto.class))).willReturn(savedBeerDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/beer/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(beerDtoJson))
            .andExpect(status().isCreated());

        BDDMockito.then(beerService).should().saveBeer(any(BeerDto.class));
    }

    @Test
    void handleUpdate() throws Exception {
        BeerDto beerDto = validBeerDto;
        beerDto.setUuid(null);
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/beer/{beerId}", UUID.randomUUID())
            .contentType(MediaType.APPLICATION_JSON)
            .content(beerDtoJson))
            .andExpect(status().isNoContent());

        BDDMockito.then(beerService).should().updateBeer(any(UUID.class), any(BeerDto.class));
    }

    @Test
    void deleteBeer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/beer/{beerId}", UUID.randomUUID())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        BDDMockito.then(beerService).should().deleteById(any(UUID.class));
    }

}