package pl.krzys.kukla.msscbrewery.service;

import org.springframework.stereotype.Service;
import pl.krzys.kukla.msscbrewery.web.model.BeerDto;

import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
@Service
public class BeerServiceImpl implements BeerService {
    @Override
    public BeerDto getBeerById(UUID beerId) {
        BeerDto beerDto = BeerDto.builder()
            .uuid(UUID.randomUUID())
            .beerName("beerName")
            .beerStyle("beerStyle")
            .build();
        return beerDto;
    }

}
