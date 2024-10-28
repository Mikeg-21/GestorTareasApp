package com.gestor.tareas.com.gestor.tareas.service;

    public interface IEmailNotificationService {
        void sendReminderEmail(String to, String subject, String body);
    }


