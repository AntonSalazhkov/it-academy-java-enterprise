package by.it.academy.shop.services.mail;

import by.it.academy.shop.dtos.mail.requests.CreateMailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Реализация сервиса отправки сообщения.
 */

@Service
@RequiredArgsConstructor
public class MailApiService implements MailService {

    @Value("${services.mail-api.url}")
    private String url;

    @Override
    public boolean createMessage(String email, String subject, String text) {
        CreateMailRequest createMailRequest = new CreateMailRequest(email, subject, text);
        return dispatchMessage(createMailRequest);
    }

    @Override
    public boolean dispatchMessage(CreateMailRequest createMailRequest) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, createMailRequest, CreateMailRequest.class);
        return true;
    }
}
