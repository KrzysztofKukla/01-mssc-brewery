package pl.krzys.kukla.msscbrewery.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.krzys.kukla.msscbrewery.service.BeerService;
import pl.krzys.kukla.msscbrewery.web.model.BeerDto;

import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
@RequestMapping("/api/v1/beer")
@RestController
@RequiredArgsConstructor
public class BeerController {

    private final BeerService beerService;

    @GetMapping("/{beerId}")
    //we can use here BeerDto as well and @RestController will serialize that to json
    //but @ResponseEntity gives you more control and more flexibility to manage object
    public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId) {

        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BeerDto> handlePost(BeerDto beerDto) {
        BeerDto savedBeer = beerService.saveBeer(beerDto);

        //here ResponseEntity should have location header on it
        //so after creation we want to return back to the client URL where it was created at
        HttpHeaders httpHeaders = new HttpHeaders();
        //TODO add hostname to url
        httpHeaders.add("Location", "/api/v1/beer" + savedBeer.getUuid().toString());

        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
    }

}
