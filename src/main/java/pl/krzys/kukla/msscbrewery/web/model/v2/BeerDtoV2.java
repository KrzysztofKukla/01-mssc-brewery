package pl.krzys.kukla.msscbrewery.web.model.v2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDtoV2 {

    private UUID uuid;
    private String beerName;
    private BeerStyleEnum beerStyle;
    private Long upc;

}
