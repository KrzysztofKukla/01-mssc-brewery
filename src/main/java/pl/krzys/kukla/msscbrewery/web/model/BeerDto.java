package pl.krzys.kukla.msscbrewery.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
// Lombok allows to generate all required method in compile time
// we need to
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//DTO ( Data Transfer Object ) is needed for working with web layer
public class BeerDto {

    @Null // not allow client to setup  id
    private UUID uuid;

    @NotBlank
    @Size(min = 3, max = 100)
    private String beerName;

    @NotBlank
    private String beerStyle;

    @Positive
    private Long upc;

    //good type to use in public interfaces
    @Null
    private OffsetDateTime createdDate;

    @Null
    private OffsetDateTime lastUpdatedDate;

    @Positive
    private Integer quantityOnHand;

}
