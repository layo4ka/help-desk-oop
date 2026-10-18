package helpdesk.service;

/**
 * Реализация уведомлений через консоль (задание 10).
 */
public class ConsoleNotificationService implements NotificationService {

    @Override
    public void send(String message) {
        System.out.println("[УВЕДОМЛЕНИЕ] " + message);
    }
}
