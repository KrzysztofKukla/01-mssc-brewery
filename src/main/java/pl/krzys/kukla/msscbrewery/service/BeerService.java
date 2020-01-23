package pl.krzys.kukla.msscbrewery.service;

import pl.krzys.kukla.msscbrewery.web.model.BeerDto;

import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
public interface BeerService {
    BeerDto getBeerById(UUID beerId);

}
