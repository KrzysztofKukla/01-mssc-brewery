package pl.krzys.kukla.msscbrewery.service.v2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.krzys.kukla.msscbrewery.web.model.v2.BeerDtoV2;

import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
@Slf4j
@Service
public class BeerServiceV2Impl implements BeerServiceV2 {
    @Override
    public BeerDtoV2 getBeerById(UUID beerId) {
        return null;
    }

    @Override
    public BeerDtoV2 saveBeer(BeerDtoV2 beerDto) {
        return null;
    }

    @Override
    public void updateBeer(UUID beerId, BeerDtoV2 beerDto) {

    }

    @Override
    public void deleteById(UUID beerId) {

    }

}
