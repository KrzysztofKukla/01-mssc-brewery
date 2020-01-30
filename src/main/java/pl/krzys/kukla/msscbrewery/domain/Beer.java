package pl.krzys.kukla.msscbrewery.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.krzys.kukla.msscbrewery.web.model.v2.BeerStyleEnum;

import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
// Lombok allows to generate all required method in compile time
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Beer {
    private UUID uuid;
    private String beerName;
    private BeerStyleEnum beerStyle;
    private Long upc;

}
