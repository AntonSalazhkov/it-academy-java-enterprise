package by.it.academy.entities.purchase;

import by.it.academy.entities.product.Product;
import by.it.academy.entities.user.User;
import by.it.academy.util.annotation.SetUUID;
import by.it.academy.util.annotation.SetUUIDAnnotationAnalyzer;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Сущность Purchase, записываемая в базу данных как таблица PURCHASE.
 * Дополнительно имеется конструктор без поля "id".
 *
 * @SetUUID является собственной аннотацией с вызовом обработчика в конструкторе как SetUUIDAnnotationAnalyzer.parse(this);
 * Аннотация устанавливает полю "id" - UUID, если таковое имеется и оно равно null.
 * В сущности имеются отношения @ManyToOne к User и аналогичное отношение @ManyToOne к Product.
 * Поле localDate устанавливается в соответствующем формате при создании сущности.
 */

@Entity
@Table(name = "PURCHASE_4")
@Data
@NoArgsConstructor
@SetUUID           // Own annotation
public class Purchase {

    @Id
    private String id;

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
        SetUUIDAnnotationAnalyzer.parse(this);
        this.user = user;
        this.product = product;
        this.productQuantity = productQuantity;
    }
}
