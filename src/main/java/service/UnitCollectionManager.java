package service;

import domain.Unit;
import java.time.Instant;
import java.util.*;
import static validation.UnitValidator.validateUnit;

public class UnitCollectionManager {

    private final Map<Long, Unit> unitCollection = new LinkedHashMap<>();

    private long getUnitNextId() {
        return System.currentTimeMillis() + unitCollection.size();
    }

    public Unit addUnit(String code, String name, String ownerUsername) {
        long id = getUnitNextId();
        Unit unit = new Unit(id, code, name, ownerUsername, Instant.now(), Instant.now());
        validateUnit(unit);
        unitCollection.put(unit.getId(), unit);
        return unit;
    }

    public void getUnitById(long id) {
        Unit unit = unitCollection.get(id);
        if (unit != null) {
            System.out.println("ID: " + unit.getId());
            System.out.println("Code: " + unit.getCode());
            System.out.println("Name: " + unit.getName());
        } else throw new NoSuchElementException("Единица с уникальным номером(ID) " + id + " не найдена");
    }

    public Unit getUnitByCode(String code) {
        for (Unit unit : unitCollection.values()) {
            if (unit.getCode().equals(code)) {
                return unit;
            }
        }
        return null;
    }

    public void getAllUnits() {
        System.out.printf("%-15s %-10s %-30s%n", "ID", "Code", "Name");
        for (Unit unit : unitCollection.values()) {
            System.out.printf("%-15d %-10s %-30s%n",
                    unit.getId(),
                    unit.getCode(),
                    unit.getName());
        }
    }

    public void updateUnitCode(long id, String code, String ownerUsername) {
        Unit unit = unitCollection.get(id);
        if (unit != null) {
        unit.setCode(code);
        unit.setOwnerUsername(ownerUsername);
        unit.setUpdatedAt(Instant.now());
        validateUnit(unit);
        } else throw new NoSuchElementException("Единица с уникальным номером(ID) " + id + " не найдена");
    }

    public void updateUnitName(long id, String name, String ownerUsername) {
        Unit unit = unitCollection.get(id);
        if (unit != null) {
            unit.setName(name);
            unit.setOwnerUsername(ownerUsername);
            unit.setUpdatedAt(Instant.now());
            validateUnit(unit);
        } else throw new NoSuchElementException("Единица с уникальным номером(ID) " + id + " не найдена");
    }

    public void removeUnit(long id) {
        Unit unit = unitCollection.get(id);
        if (unit != null) {
            unitCollection.remove(id);
        } else throw new NoSuchElementException("Единица с уникальным номером(ID) " + id + " не найдена");
    }
}

