package service;

import domain.Unit;
import utility.IdGenerator;

import java.time.Instant;
import java.util.*;

import static validation.UnitValidator.validateUnit;

public class UnitCollectionManager {

    private final Map<Long, Unit> unitCollection = new LinkedHashMap<>();

    public Unit addUnit(String code, String name, String ownerUsername) {
        long id = IdGenerator.generateID(unitCollection.size());
        Unit unit = new Unit(id, code, name, ownerUsername, Instant.now(), Instant.now());
        validateUnit(unit);
        unitCollection.put(unit.getId(), unit);
        return unit;
    }

    public Unit getUnitById(long id) {
        Unit unit = unitCollection.get(id);
        if (unit != null) {
            return unit;
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

    public Unit getUnitByName(String name) {
        for (Unit unit : unitCollection.values()) {
            if (unit.getName().equals(name)) {
                return unit;
            }
        }
        return null;
    }

    public Collection<Unit> getAllUnits() {
        return unitCollection.values();
    }

    public void updateUnitCode(long id, String code, String ownerUsername) {
        Unit unit = unitCollection.get(id);
        if (unit == null) {
            throw new NoSuchElementException("Единица с уникальным номером(ID) " + id + " не найдена");
        } else {
            unit.setCode(code);
            unit.setOwnerUsername(ownerUsername);
            unit.setUpdatedAt(Instant.now());
            validateUnit(unit);
        }

    }

    public void updateUnitName(long id, String name, String ownerUsername) {
        Unit unit = unitCollection.get(id);
        if (unit == null) {
            throw new NoSuchElementException("Единица с уникальным номером(ID) " + id + " не найдена");
        } else {
            unit.setName(name);
            unit.setOwnerUsername(ownerUsername);
            unit.setUpdatedAt(Instant.now());
            validateUnit(unit);
        }
    }

    public void removeUnit(long id) {
        Unit unit = unitCollection.get(id);
        if (unit != null) {
            unitCollection.remove(id);
        } else throw new NoSuchElementException("Единица с уникальным номером(ID) " + id + " не найдена");
    }
}

