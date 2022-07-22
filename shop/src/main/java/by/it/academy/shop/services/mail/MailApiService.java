package by.it.academy.shop.services.mail;

import by.it.academy.shop.dtos.mail.responses.MailResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Реализация сервиса отправки сообщения.
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class MailApiService implements MailService {

    @Value("${services.mail-api.url}")
    private String url;

    @Override
    public void createMessage(String email, String subject, String text) {
        MailResponse mailResponse = new MailResponse(email, subject, text);
        dispatchMessage(mailResponse);
    }

    @Override
    public void dispatchMessage(MailResponse mailResponse) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, mailResponse, MailResponse.class);
    }
}
