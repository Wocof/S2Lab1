package commands;

import service.UnitCollectionManager;
import utility.LongIDReader;

import java.util.NoSuchElementException;

public class UnitDelete implements ExecuteCommand {

    private final LongIDReader longIDReader;
    private final UnitCollectionManager unitCollectionManager;

    public UnitDelete(LongIDReader longIDReader, UnitCollectionManager unitCollectionManager) {
        this.longIDReader = longIDReader;
        this.unitCollectionManager = unitCollectionManager;
    }

    @Override
    public boolean executeCommand() {
        while (true) {
            try {
                Long id = longIDReader.readLongID();
                unitCollectionManager.removeUnit(id);
                System.out.println("OK, deleted");
                break;
            } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            }
        }
        return true;
    }
}
