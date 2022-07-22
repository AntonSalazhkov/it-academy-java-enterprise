package by.it.academy.mailapi.services.mail;

import by.it.academy.mailapi.dtos.mail.requests.MailRequest;
import by.it.academy.mailapi.dtos.mail.responses.MailResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Реализация сервиса обработки отправки сообщения.
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class MailApiService implements MailService {

    @Value("${services.mail.email}")
    private String email;

    @Value("${services.mail.password}")
    private String password;

    @Value("${services.mail.urlResponse}")
    private String urlResponse;

    @Override
    public void dispatchStatus(MailResponse mailResponse) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(urlResponse, mailResponse, MailResponse.class);
    }

    @Override
    public boolean dispatchMessage(MailRequest mailRequest) {
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.mail.ru");
        props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailRequest.getEmail()));
            message.setSubject(mailRequest.getSubject());
            message.setText(mailRequest.getText());

            Transport.send(message);

            return true;
        } catch (MessagingException e) {
            log.info(e.getMessage());
            return false;
        }
    }
}
