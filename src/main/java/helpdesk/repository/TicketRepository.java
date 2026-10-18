package helpdesk.repository;

import helpdesk.model.Ticket;

import java.util.ArrayList;
import java.util.List;

/**
 * Хранилище заявок (задание 12).
 * findAll() возвращает копию списка: внешний код не может
 * напрямую изменять внутреннюю коллекцию — инкапсуляция на уровне коллекции.
 */
public class TicketRepository {

    private final List<Ticket> tickets = new ArrayList<>();

    public void add(Ticket ticket) {
        tickets.add(ticket);
    }

    public List<Ticket> findAll() {
        return new ArrayList<>(tickets);
    }
}
