package commands;

import domain.Unit;
import service.UnitCollectionManager;
import utility.LongIDReader;


import java.util.NoSuchElementException;

public class UnitShow implements ExecuteCommand {

    private final LongIDReader longIDReader;
    private final UnitCollectionManager unitCollectionManager;

    public UnitShow(LongIDReader longIDReader, UnitCollectionManager unitCollectionManager) {
        this.longIDReader = longIDReader;
        this.unitCollectionManager = unitCollectionManager;
    }

    @Override
    public boolean executeCommand() {
        while (true) {
            try {
                Long id = longIDReader.readLongID();
                Unit unit = unitCollectionManager.getUnitById(id);
                System.out.println("ID: " + unit.getId());
                System.out.println("Code: " + unit.getCode());
                System.out.println("Name: " + unit.getName());
                break;
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
            }
        }
        return true;
    }
}
