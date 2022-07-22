package by.it.academy.shop.dtos.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Сооставляемое сообщение для вывода на странице о выявленных некорректных ситуаций в работе программы.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseError {
    private String message;
    private String error;
}
