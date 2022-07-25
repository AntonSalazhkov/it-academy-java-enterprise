package by.it.academy.mailapi.dtos.mail.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Ответ о статусе отправки.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailResponse {

    private boolean statusMail;
}
