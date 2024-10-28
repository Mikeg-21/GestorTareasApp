package com.gestor.tareas.com.gestor.tareas.service.impl;

import com.gestor.tareas.com.gestor.tareas.service.IEmailNotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationServiceImpl implements IEmailNotificationService {

    private static final Logger logger = LoggerFactory.getLogger(EmailNotificationServiceImpl.class);

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    public void sendReminderEmail(String to, String subject, String body) {
        logger.info("Iniciando envío de correo a: {}", to);

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(formatEmailBody(body));

            logger.debug("Configuración del mensaje:");
            logger.debug("From: {}", fromEmail);
            logger.debug("To: {}", to);
            logger.debug("Subject: {}", subject);
            logger.debug("Body: {}", formatEmailBody(body));

            // Enviar el email
            mailSender.send(message);
            logger.info("Email enviado exitosamente a: {}", to);

        } catch (Exception e) {
            logger.error("Error al enviar email a {}: {}", to, e.getMessage());
            logger.error("Stacktrace completo:", e);
            throw new RuntimeException("Error al enviar el correo electrónico", e);
        }
    }

    private String formatEmailBody(String body) {
        StringBuilder formattedBody = new StringBuilder();
        formattedBody.append("Notificación del Sistema de Gestión de Tareas\n");
        formattedBody.append("================================================\n\n");
        formattedBody.append(body);
        formattedBody.append("\n\n");
        formattedBody.append("Este es un mensaje automático, por favor no responder.");

        return formattedBody.toString();
    }

    // Método de prueba
    public void testEmail() {
        String testEmail = "mikegutierrez521@gmail.com"; // colocar email de prueba
        String subject = "Test de Conexión - Sistema de Tareas";
        String body = "Este es un mensaje de prueba enviado el: " + java.time.LocalDateTime.now();

        sendReminderEmail(testEmail, subject, body);
    }
}