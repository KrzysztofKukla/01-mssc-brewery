package pl.krzys.kukla.msscbrewery.web.mapper;

import org.mapstruct.Mapper;
import pl.krzys.kukla.msscbrewery.domain.Beer;
import pl.krzys.kukla.msscbrewery.web.model.BeerDto;

/**
 * @author Krzysztof Kukla
 */
//it will generate BeerMapperImpl class with implemented methods
@Mapper
public interface BeerMapper {
    Beer beerDtoToBeer(BeerDto beerDto);

    BeerDto BeerToBeerDto(Beer beer);

}
