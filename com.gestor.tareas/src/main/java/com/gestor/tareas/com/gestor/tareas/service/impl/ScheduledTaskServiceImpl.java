package com.gestor.tareas.com.gestor.tareas.service.impl;

import com.gestor.tareas.com.gestor.tareas.model.Tarea;
import com.gestor.tareas.com.gestor.tareas.repository.ITareaRepository;
import com.gestor.tareas.com.gestor.tareas.service.IEmailNotificationService;
import com.gestor.tareas.com.gestor.tareas.service.IScheduledTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ScheduledTaskServiceImpl implements IScheduledTaskService {

    @Autowired
    private ITareaRepository tareaRepository;

    @Autowired
    private IEmailNotificationService emailNotificationService;

    @Scheduled(fixedRate = 60000) // Configurado para las 9 AM todos los días 0 0 9 * * ?
    public void scheduleTaskReminders() {
        try {
            Date now = new Date();
            List<Tarea> tareas = tareaRepository.findAll();
            System.out.println("Iniciando revisión de " + tareas.size() + " tareas para envío de recordatorios");

            for (Tarea tarea : tareas) {
                if (!tarea.isCompletada() && tarea.getFechaLimite().before(new Date(now.getTime() + 24 * 60 * 60 * 1000))) {
                    String subject = "Recordatorio: " + tarea.getTitulo() + " - Fecha límite próxima";
                    String body = String.format(
                            "Estimado/a usuario/a,\n\n" +
                                    "Le recordamos que la tarea '%s' vence el %s.\n\n" +
                                    "Descripción de la tarea: %s\n" +
                                    "Estado actual: %s\n",
                            tarea.getTitulo(),
                            tarea.getFechaLimite(),
                            tarea.getDescripcion(),
                            tarea.isCompletada() ? "Completada" : "Pendiente"
                    );

                    System.out.println("Enviando recordatorio para la tarea: " + tarea.getTitulo());
                    emailNotificationService.sendReminderEmail(tarea.getUsuario().getEmail(), subject, body);
                }
            }
        } catch (Exception e) {
            System.err.println("Error en el proceso de recordatorios: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
