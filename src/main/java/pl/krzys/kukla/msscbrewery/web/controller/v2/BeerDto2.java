package pl.krzys.kukla.msscbrewery.web.controller.v2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.krzys.kukla.msscbrewery.web.model.v2.BeerStyleEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
// Lombok allows to generate all required method in compile time
// we need to
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//DTO ( Data Transfer Object ) is needed for working with web layer
public class BeerDto2 {

    @Null // not allow client to setup  id
    private UUID uuid;

    @NotBlank
    private String beerName;

    @NotNull
    private BeerStyleEnum beerStyle;

    @Positive
    private Long upc;

}
