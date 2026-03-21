package commands;

import service.UnitCollectionManager;
import utility.CodeReader;
import utility.LongIDReader;
import utility.NameReader;
import utility.OwnerUsernameReader;

import java.util.Scanner;

public class UnitUpdate implements ExecuteCommand {

    private final LongIDReader longIDReader;
    private final CodeReader codeReader;
    private final NameReader nameReader;
    private final OwnerUsernameReader ownerUsernameReader;
    private final UnitCollectionManager unitCollectionManager;
    private final Scanner scanner;

    public UnitUpdate(LongIDReader longIDReader, CodeReader codeReader, NameReader nameReader, OwnerUsernameReader ownerUsernameReader, UnitCollectionManager unitCollectionManager) {

        this.longIDReader = longIDReader;
        this.codeReader = codeReader;
        this.nameReader = nameReader;
        this.ownerUsernameReader = ownerUsernameReader;
        this.unitCollectionManager = unitCollectionManager;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public boolean executeCommand() {
        Long id = longIDReader.readLongID();
        while (true) {
            System.out.print("Введите поле, которое хотите обновить (code или name): ");
            String input = scanner.nextLine().trim();
            String field = input.toLowerCase();
            try {
                switch (field) {
                    case "code":
                        String code = codeReader.readCode();
                        String ownerUsername1 = ownerUsernameReader.readOwnerUsername();
                        unitCollectionManager.updateUnitCode(id, code, ownerUsername1);
                        System.out.println("OK");
                        return true;
                    case "name":
                        String name = nameReader.readName();
                        String ownerUsername2 = ownerUsernameReader.readOwnerUsername();
                        unitCollectionManager.updateUnitName(id, name, ownerUsername2);
                        System.out.println("OK");
                        return true;
                    default:
                        throw new IllegalArgumentException("Данное поле нельзя обновить или его не существует у единицы");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}