package by.training.java.module_6.task01.logic;

import by.training.java.module_6.task01.user.Account;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Dispatch {

    public Dispatch() {
    }

    public void dispatchMessage(String subject, String text, Account account) {

        final String email = "test871150@gmail.com";
        final String password = "1234qwer!@#$";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(account.getEmail()));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);
            System.out.println("Sent");

        } catch (MessagingException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dispatchToAllUsers(String subject, String text) {
        Account account = new Account();
        account.readAccount();

        for (int i = 0; i < account.getAccounts().size(); i++) {
            dispatchMessage(subject, text, account.getAccounts().get(i));
        }
    }

    public boolean dispatchToAdministrator(String subject, String text) {
        Account account = new Account();
        account.readAccount();
        boolean successfulDispatch = false;

        for (int i = 0; i < account.getAccounts().size(); i++) {
            if (account.getAccounts().get(i).getUserStatus().compareTo("administrator") == 0) {
                dispatchMessage(subject, text, account.getAccounts().get(i));

                successfulDispatch = true;

            }
        }
        return successfulDispatch;
    }
}
