package by.it.academy.shop.dtos.product.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowDetailsRequest {

    @NotBlank
    private UUID id;
}
