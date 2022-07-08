package by.it.academy.shop.services.mail;

import by.it.academy.shop.constants.Messages;
import by.it.academy.shop.entities.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailApiService implements MailService {

    @Value("${services.mail.email}")
    private String email;

    @Value("${services.mail.password}")
    private String password;

    @Override
    public void dispatchMessage(User user) {
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
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
            message.setSubject(Messages.SUBJECT_EMAIL_MESSAGE);
            message.setText(Messages.TEXT_EMAIL_MESSAGE);

            Transport.send(message);
            log.info("Message sent to email: " + user.getEmail());

        } catch (MessagingException e) {
            log.info(e.getMessage());
        }
    }
}
