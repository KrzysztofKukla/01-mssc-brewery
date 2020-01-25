package pl.krzys.kukla.msscbrewery.service.v2;

import pl.krzys.kukla.msscbrewery.web.model.v2.BeerDtoV2;

import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
public interface BeerServiceV2 {
    BeerDtoV2 getBeerById(UUID beerId);

    BeerDtoV2 saveBeer(BeerDtoV2 beerDto);

    void updateBeer(UUID beerId, BeerDtoV2 beerDto);

    void deleteById(UUID beerId);

}
