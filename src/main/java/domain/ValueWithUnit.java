package domain;

import java.util.Objects;

public final class ValueWithUnit {

    // Числовое значение.
    private double value;

    // Код единицы (например "mg/L"). Нельзя пустое.
    private String unitCode;

    public ValueWithUnit(double value, String unitCode) {
        this.value = value;
        this.unitCode = unitCode;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ValueWithUnit that = (ValueWithUnit) o;
        return Double.compare(value, that.value) == 0 && Objects.equals(unitCode, that.unitCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unitCode);
    }

    @Override
    public String toString() {
        return "ValueWithUnit{" +
                "value=" + value +
                ", unitCode='" + unitCode + '\'' +
                '}';
    }
}
