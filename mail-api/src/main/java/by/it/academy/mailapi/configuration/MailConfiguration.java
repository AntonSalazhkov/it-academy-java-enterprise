package by.it.academy.mailapi.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурационные данные для сервиса обработки отправки сообщения.
 */

@Data
@Configuration
@ConfigurationProperties("services.mail")
public class MailConfiguration {
    private String email;
    private String password;
    private String url;
}
