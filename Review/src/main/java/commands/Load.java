package commands;

import service.ConversionRuleCollectionManager;
import service.UnitCollectionManager;
import storage.StorageFile;
import storage.StorageData;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static storage.FileValidator.validateFile;

public class Load implements ArgumentCommand {

    private final UnitCollectionManager unitCollectionManager;
    private final ConversionRuleCollectionManager conversionRuleCollectionManager;
    private final Scanner scanner;

    public Load(UnitCollectionManager unitCollectionManager, ConversionRuleCollectionManager conversionRuleCollectionManager, Scanner scanner) {
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
            System.out.print("Введите путь к файлу для загрузки: ");
            path = scanner.nextLine().trim();
            if (path.isEmpty()) {
                System.out.println("Путь к файлу не может быть пустым");
                return true;
            }
        }
        try {
            StorageData storageData = new StorageFile().loadFromFile(path);
            List<String> errors = validateFile(storageData);
            if (!errors.isEmpty()) {
                System.out.println("Невозможно загрузить файл. Список ошибок: ");
                for (String err : errors) {
                    System.out.println("  - " + err);
                }
                return true;
            }
            unitCollectionManager.loadUnits(storageData.getUnits());
            conversionRuleCollectionManager.loadConversionRules(storageData.getConversionRules());
            System.out.println("Данные успешно загружены из " + path);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Ошибка загрузки: несовместимая версия классов.");
        }
        return true;
    }

}
