package domain;

import java.time.Instant;
import java.util.Objects;

public final class ConversionRule {
    // Уникальный номер правила. Программа назначает сама.
    private final long id;

    // Из какой единицы (код, например "mg/L"). Нельзя пустое.
    private String fromUnitCode;

    // В какую единицу (код, например "g/L"). Нельзя пустое.
    private String toUnitCode;

    // Коэффициент: result = value * factor. Должен быть > 0.
    private double factor;

    // Кто создал правило (логин). На ранних этапах можно "SYSTEM".
    private String ownerUsername;

    // Когда создано. Программа ставит автоматически.
    private final Instant createdAt;

    // Когда обновляли. Программа обновляет автоматически.
    private Instant updatedAt;

    public ConversionRule(long id, String fromUnitCode, String toUnitCode, double factor, String ownerUsername, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.fromUnitCode = fromUnitCode;
        this.toUnitCode = toUnitCode;
        this.factor = factor;
        this.ownerUsername = ownerUsername;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public String getFromUnitCode() {
        return fromUnitCode;
    }

    public String getToUnitCode() {
        return toUnitCode;
    }

    public double getFactor() {
        return factor;
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

    public void setFromUnitCode(String fromUnitCode) {
        this.fromUnitCode = fromUnitCode;
    }

    public void setToUnitCode(String toUnitCode) {
        this.toUnitCode = toUnitCode;
    }

    public void setFactor(double factor) {
        this.factor = factor;
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
        ConversionRule that = (ConversionRule) o;
        return id == that.id && Double.compare(factor, that.factor) == 0 && Objects.equals(fromUnitCode, that.fromUnitCode) && Objects.equals(toUnitCode, that.toUnitCode) && Objects.equals(ownerUsername, that.ownerUsername) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fromUnitCode, toUnitCode, factor, ownerUsername, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "ConversionRule{" +
                "id=" + id +
                ", fromUnitCode='" + fromUnitCode + '\'' +
                ", toUnitCode='" + toUnitCode + '\'' +
                ", factor=" + factor +
                ", ownerUsername='" + ownerUsername + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
