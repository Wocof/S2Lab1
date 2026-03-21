package commands;

import domain.Unit;
import service.UnitCollectionManager;

import java.util.Collection;

public class UnitsList implements ExecuteCommand {

    private final UnitCollectionManager unitCollectionManager;

    public UnitsList(UnitCollectionManager unitCollectionManager) {
        this.unitCollectionManager = unitCollectionManager;
    }

    @Override
    public boolean executeCommand() {
        Collection<Unit> units = unitCollectionManager.getAllUnits();
        System.out.printf("%-15s %-10s %-30s%n", "ID", "Code", "Name");
        for (Unit unit : units) {
            System.out.printf("%-15d %-10s %-30s%n",
                    unit.getId(),
                    unit.getCode(),
                    unit.getName());
        }
        return true;
    }
}
