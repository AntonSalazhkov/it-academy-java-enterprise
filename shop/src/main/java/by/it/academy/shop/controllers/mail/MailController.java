package by.it.academy.shop.controllers.mail;

import by.it.academy.shop.dtos.mail.requests.MailRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер обработки отправляемых email сообщений.
 */

@Slf4j
@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {

    /**
     * Метод getStatusMail прослушивает адресс /mail и логирует поступающий ответ о статусе отправки сообщения.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void getStatusMail(@RequestBody @Validated MailRequest mailRequest) {
        log.info("Response received after sending a message: {}", mailRequest.isStatusMail());
    }
}
