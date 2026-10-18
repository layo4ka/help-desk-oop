package helpdesk.model;

/**
 * Базовый класс пользователя (задания 6 и 9).
 * Абстрактный: в системе не существует пользователя без конкретной роли,
 * поэтому создать new User(...) нельзя.
 */
public abstract class User {
    private final long id;
    private final String name;
    private final String email;

    public User(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // каждый наследник обязан описать своё действие — это абстракция
    public abstract void performAction();

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
