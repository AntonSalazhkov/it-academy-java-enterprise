package by.it.academy.mailapi.controllers.mail;

import by.it.academy.mailapi.dtos.mail.requests.MailRequest;
import by.it.academy.mailapi.dtos.mail.responses.MailResponse;
import by.it.academy.mailapi.services.mail.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * Контроллер обработки отправки сообщений .
 * Метод sendMessage прослушивает адресс /send-message, в случае корректности поступивших данных,
 * отправит сообщение по указанному адресу, с соответствующим заголовком и сообщением. Вернет булево выражение.
 */

@Slf4j
@RestController
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @RequestMapping("/send-message")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void sendMessage(@RequestBody @Validated MailRequest mailRequest) {
        log.info("Received MailRequest by \"/send-message\": " + mailRequest.toString());

        if (mailService.dispatchMessage(mailRequest)) {
            log.info("Result of \"sendMessage\" method: true");
            mailService.dispatchStatus(new MailResponse(true));

        } else {
            log.info("Result of \"sendMessage\" method: false");
            mailService.dispatchStatus(new MailResponse(false));
        }
    }
}
