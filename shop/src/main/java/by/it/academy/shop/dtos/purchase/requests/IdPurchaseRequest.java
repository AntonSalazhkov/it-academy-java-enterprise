package by.it.academy.shop.dtos.purchase.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdPurchaseRequest {

    @NotNull
    private UUID id;
}
