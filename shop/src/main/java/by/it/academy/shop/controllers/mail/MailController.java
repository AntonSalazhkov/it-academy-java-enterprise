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
 * Метод getDispatchStatus прослушивает адресс /dispatch-status
 * и логирует поступающий ответ о статусе отправки сообщения.
 */

@Slf4j
@RestController
@RequiredArgsConstructor
public class MailController {

    @RequestMapping(value = "/dispatch-status")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void getDispatchStatus(@RequestBody @Validated MailRequest mailRequest) {
        log.info("Message was sent: " + mailRequest.isMessageDispatchStatus());
    }
}
