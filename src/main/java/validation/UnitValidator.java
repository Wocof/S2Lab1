package validation;

import domain.Unit;

public class UnitValidator {

    public static void validateCode(String code) {
        if (code == null) {
            throw new IllegalArgumentException("Короткий код единицы(code) не может быть null");
        }
        if (code.isEmpty()) {
            throw new IllegalArgumentException("Короткий код единицы(code) не может быть пустым");
        }
        if (code.length() > 16) {
            throw new IllegalArgumentException("Короткий код единицы(code) не может быть длиннее 16 символов");
        }
    }

    public static void validateName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Название единицы(name) не может быть null");
        }
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Название единицы(name) не может быть пустым");
        }
        if (name.length() > 64) {
            throw new IllegalArgumentException("Название единицы(name) не может быть длиннее 64 символов");
        }
    }

    public static void validateOwnerUsername(String ownerUsername) {
        if (ownerUsername == null) {
            throw new IllegalArgumentException("Логин(ownerUsername) не может быть null");
        }
    }

    public static void validateUnit(Unit unit) {
        validateCode(unit.getCode());
        validateName(unit.getName());
        validateOwnerUsername(unit.getOwnerUsername());
    }
}
