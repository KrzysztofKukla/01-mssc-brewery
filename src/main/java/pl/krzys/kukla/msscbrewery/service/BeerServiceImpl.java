package pl.krzys.kukla.msscbrewery.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.krzys.kukla.msscbrewery.web.model.BeerDto;

import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
@Slf4j
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

    @Override
    public BeerDto saveBeer(BeerDto beerDto) {
        return BeerDto.builder()
            .uuid(UUID.randomUUID())
            .beerName(beerDto.getBeerName())
            .beerStyle(beerDto.getBeerStyle())
            .build();

    }

    @Override
    public void updateBeer(UUID beerId, BeerDto beerDto) {
        //TODO
    }

    @Override
    public void deleteById(UUID beerId) {
        log.debug("Deleting beer object...");
        //TODO
    }

}
