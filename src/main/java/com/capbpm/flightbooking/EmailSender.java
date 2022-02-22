package com.capbpm.flightbooking;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

@Component
public class EmailSender {
    @Value("${spring.mail.smtp.auth}")
    String auth;

    @Value("${spring.mail.smtp.starttlsenable}")
    String starttlsenable;

    @Value("${spring.mail.smtp.host}")
    String host;

    @Value("${spring.mail.smtp.port}")
    String port;

    @Value("${spring.mail.smtp.sslprotocols}")
    String sslprotocols;

    @Value("${spring.mail.smtp.username}")
    String username;

    @Value("${spring.mail.smtp.password}")
    String password;

    public boolean sendEmail(String receiverName, String receiverEmail, String subject, String content) {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", auth);
        prop.put("mail.smtp.starttls.enable", starttlsenable);
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", port);
        prop.put("mail.smtp.ssl.protocols", sslprotocols);

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("no-reply@capbpm.com"));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(receiverEmail));
            message.setSubject(subject);

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(content, "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);
        } catch (MessagingException e) {
            System.out.println(e.getMessage());

            return false;
        }

        return true;
    }
}