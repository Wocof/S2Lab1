package commands;

import service.ConversionRuleCollectionManager;
import service.UnitCollectionManager;
import storage.StorageFile;
import storage.StorageData;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Save implements ArgumentCommand {

    private final UnitCollectionManager unitCollectionManager;
    private final ConversionRuleCollectionManager conversionRuleCollectionManager;
    private final Scanner scanner;

    public Save(UnitCollectionManager unitCollectionManager, ConversionRuleCollectionManager conversionRuleCollectionManager, Scanner scanner) {
        this.unitCollectionManager = unitCollectionManager;
        this.conversionRuleCollectionManager = conversionRuleCollectionManager;
        this.scanner = scanner;
    }

    @Override
    public boolean executeCommand() {
        return executeCommand(new String[0]);
    }

    @Override
    public boolean executeCommand(String[] args) {
        String path;
        if (args.length > 0 && args[0] != null && !args[0].trim().isEmpty()) {
            path = args[0].trim();
        } else {
            System.out.print("Введите путь к файлу для сохранения: ");
            path = scanner.nextLine().trim();
            if (path.isEmpty()) {
                System.out.println("Путь не может быть пустым.");
                return true;
            }
        }
        try {
            StorageData storageData = new StorageData(
                    new ArrayList<>(unitCollectionManager.getAllUnits()),
                    new ArrayList<>(conversionRuleCollectionManager.getAllConversionRules())
            );
            new StorageFile().saveToFile(path, storageData);
            System.out.println("Данные сохранены в " + path);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

}
