package by.it.academy.shop.entities.purchase;

import by.it.academy.shop.entities.product.Product;
import by.it.academy.shop.entities.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * Сущность Purchase, записываемая в базу данных как таблица PURCHASE.
 * В сущности имеются отношения @ManyToOne к User и аналогичное отношение @ManyToOne к Product.
 * Поле localDate устанавливается в соответствующем формате при создании сущности.
 */

@Entity
@Table(name = "PURCHASE_4")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "Product_id")
    private Product product;

    @Column(name = "PRODUCT_QUANTITY")
    private int productQuantity;

    @Column(name = "LOCAL_DATE")
    private String localDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));

    public Purchase(User user, Product product, int productQuantity) {
        this.user = user;
        this.product = product;
        this.productQuantity = productQuantity;
    }
}
