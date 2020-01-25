package pl.krzys.kukla.msscbrewery.web.controller.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.krzys.kukla.msscbrewery.service.v2.BeerServiceV2;
import pl.krzys.kukla.msscbrewery.web.model.v2.BeerDtoV2;

import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
@RequestMapping("/api/v2/beer")
@RestController
@RequiredArgsConstructor

public class BeerControllerV2 {
    private final BeerServiceV2 beerServiceV2;

    @GetMapping("/{beerId}")
    //we can use here BeerDto as well and @RestController will serialize that to json
    //but @ResponseEntity gives you more control and more flexibility to manage object
    public ResponseEntity<BeerDtoV2> getBeer(@PathVariable("beerId") UUID beerId) {

        return new ResponseEntity<>(beerServiceV2.getBeerById(beerId), HttpStatus.OK);
    }

    @PostMapping //POST - creating new Beer
    //@RequestBody binds property from request to BeerDto object
    public ResponseEntity<BeerDtoV2> handlePost(@RequestBody BeerDtoV2 beerDto) {
        BeerDtoV2 savedBeer = beerServiceV2.saveBeer(beerDto);

        //here ResponseEntity should have location header on it
        //so after creation we want to return back to the client URL where it was created at
        HttpHeaders httpHeaders = new HttpHeaders();
        //TODO add hostname to url
        httpHeaders.add("Location", "/api/v1/beer" + savedBeer.getUuid().toString());

        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
    }

    // PUT update existing Beer
    // is idempotent - we can call that multiple times - will be affect only first time
    @PutMapping(value = "/{beerId}")
    //beerId should be passed here as above to be read only to not allow client update that property for BeerDto object
    public ResponseEntity handleUpdate(@PathVariable UUID beerId, @RequestBody BeerDtoV2 beerDto) {
        beerServiceV2.updateBeer(beerId, beerDto);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //it allows to explicitly specify status of web controller
    public void deleteBeer(@PathVariable UUID beerId) {
        beerServiceV2.deleteById(beerId);

        //we could make it as return new ResponseEntity(HttpStatus.NO_CONTENT) it allows us to make more control of that
        // for example to some headers with location etc.
    }

}
