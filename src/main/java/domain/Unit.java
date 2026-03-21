package domain;

import java.time.Instant;
import java.util.Objects;

public final class Unit {
    // Уникальный номер единицы. Программа назначает сама.
    private final long id;

    // Короткий код единицы (например "mg/L"). Нельзя пустое. До 16 символов.
    private String code;

    // Человеческое название (например "milligrams per liter"). Нельзя пустое. До 64 символов.
    private String name;

    // Кто создал (логин). На ранних этапах можно "SYSTEM".
    private String ownerUsername;

    // Когда создано. Программа ставит автоматически.
    private final Instant createdAt;

    // Когда обновляли. Программа обновляет автоматически.
    private Instant updatedAt;

    public Unit(long id, String code, String name, String ownerUsername, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.ownerUsername = ownerUsername;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Unit unit = (Unit) o;
        return id == unit.id && Objects.equals(code, unit.code) && Objects.equals(name, unit.name) && Objects.equals(ownerUsername, unit.ownerUsername) && Objects.equals(createdAt, unit.createdAt) && Objects.equals(updatedAt, unit.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name, ownerUsername, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Unit{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", ownerUsername='" + ownerUsername + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}