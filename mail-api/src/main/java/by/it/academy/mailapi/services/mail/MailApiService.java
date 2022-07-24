package by.it.academy.mailapi.services.mail;

import by.it.academy.mailapi.configuration.MailConfiguration;
import by.it.academy.mailapi.dtos.mail.requests.MailRequest;
import by.it.academy.mailapi.dtos.mail.responses.MailResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
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

    private final MailConfiguration mailConfiguration;

    @Override
    public void dispatchStatus(MailResponse mailResponse) {
        String url = mailConfiguration.getUrl();

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, mailResponse, MailResponse.class);
    }

    @Override
    public boolean dispatchMessage(MailRequest mailRequest) {
        String email = mailConfiguration.getEmail();
        String password = mailConfiguration.getPassword();

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
            dispatch(session, email, mailRequest);
            return true;
        } catch (MessagingException e) {
            log.info(e.getMessage());
            return false;
        }
    }

    private void dispatch(Session session, String email, MailRequest mailRequest) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(email));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailRequest.getEmail()));
        message.setSubject(mailRequest.getSubject());
        message.setText(mailRequest.getText());

        Transport.send(message);
    }
}
