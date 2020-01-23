package pl.krzys.kukla.msscbrewery.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class BeerDto {

    private UUID uuid;
    private String beerName;
    private String beerStyle;
    private Long upc;

}
