package commands;

import domain.Unit;
import service.UnitCollectionManager;
import utility.CodeReader;
import utility.NameReader;
import utility.OwnerUsernameReader;

public class UnitAdd implements ExecuteCommand {

    private final CodeReader codeReader;
    private final NameReader nameReader;
    private final OwnerUsernameReader ownerUsernameReader;
    private final UnitCollectionManager unitCollectionManager;

    public UnitAdd(CodeReader codeReader, NameReader nameReader, OwnerUsernameReader ownerUsernameReader, UnitCollectionManager unitCollectionManager) {
        this.codeReader = codeReader;
        this.nameReader = nameReader;
        this.ownerUsernameReader = ownerUsernameReader;
        this.unitCollectionManager = unitCollectionManager;
    }

    @Override
    public boolean executeCommand() {
        String code = codeReader.readCode();
        String name = nameReader.readName();
        String ownerUsername = ownerUsernameReader.readOwnerUsername();
        Unit unit = unitCollectionManager.addUnit(code, name, ownerUsername);
        System.out.println("OK, уникальный номер введённой единицы(ID) = " + unit.getId());
        return true;
    }
}
