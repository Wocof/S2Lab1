package cli;

import domain.ConversionRule;
import domain.Unit;
import domain.ValueWithUnit;

import service.ConversionRuleCollectionManager;
import service.UnitCollectionManager;

import java.util.NoSuchElementException;
import java.util.Scanner;

import static validation.ConversionRuleValidator.validateFactor;
import static validation.UnitValidator.*;

public class ConsoleCLI {

    private final Scanner scanner;
    private final UnitCollectionManager unitCollectionManager;
    private final ConversionRuleCollectionManager conversionRuleCollectionManager;

    public ConsoleCLI(UnitCollectionManager unitCollectionManager, ConversionRuleCollectionManager conversionRuleCollectionManager) {
        this.scanner = new Scanner(System.in);
        this.unitCollectionManager = unitCollectionManager;
        this.conversionRuleCollectionManager = conversionRuleCollectionManager;
    }

    public void executeCommand() {
        System.out.println("Справочник единиц и правил их конвертации. Для получения информации о доступных командах введите 'help'");
        while(true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) continue;

            String[] args = input.split("\\s+");
            String command = args[0].toLowerCase();

            try {
                switch(command) {
                    case "help":
                        printHelp();
                        break;

                    case "exit":
                        return;

                    case "unit_add":
                        executeUnitAdd();
                        break;

                    case "unit_list":
                        executeUnitList();
                        break;

                    case "unit_show":
                        executeUnitShow();
                        break;

                    case "unit_update":
                        executeUnitUpdate();
                        break;

                    case "unit_delete":
                        executeUnitDelete();
                        break;

                    case "conv_add":
                        executeConvAdd();
                        break;

                    case "conv_list":
                        executeConvList();
                        break;

                    case "conv_show":
                        executeConvShow();
                        break;

                    case "conv_convert":
                        executeConvConvert();
                        break;

                    case "conv_update":
                        executeConvUpdate();
                        break;

                    case "conv_check_cycle":
                        executeConvCheckCycle();
                        break;

                    case "conv_delete":
                        executeConvDelete();
                        break;

                    default: System.out.println("Неизвестная команда.");
                }
            } catch (IllegalArgumentException | NoSuchElementException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    public void printHelp() {
        System.out.println("Доступные команды:");
        System.out.println("help - получение информации о доступных командах");
        System.out.println("exit - выход из программы");
        System.out.println("unit_add - добавление новой единицы");
        System.out.println("unit_list - список всех доступных единиц");
        System.out.println("unit_show - найти единицу по уникальному номеру");
        System.out.println("unit_update - обновить информацию об единице");
        System.out.println("unit_delete - удалить единицу");
        System.out.println("conv_add - добавление нового правила конвертации");
        System.out.println("conv_list - список всех доступных правил конвертации");
        System.out.println("conv_show - найти правило конвертации по уникальному номеру");
        System.out.println("conv_convert - преобразовать единицу в другую");
        System.out.println("conv_update - обновить информацию о правиле конвертации");
        System.out.println("conv_check_cycle - грубая проверка доступных правил конвертации на циклы");
        System.out.println("conv_delete - удалить правило конвертации");
    }

    public void executeUnitAdd() {
        String code = readCode();
        String name = readName();
        String ownerUsername = readOwnerUsername();
        Unit unit = unitCollectionManager.addUnit(code, name, ownerUsername);
        System.out.println("OK, уникальный номер введённой единицы(ID) = " + unit.getId());
    }

    public void executeUnitList() {
        unitCollectionManager.getAllUnits();
    }

    public void executeUnitShow() {
        Long id = readLongID();
        unitCollectionManager.getUnitById(id);
    }

    public void executeUnitUpdate() {
        Long id = readLongID();
        while (true) {
            System.out.println("Введите поле, которое хотите обновить (code или name): ");
            String input = scanner.nextLine().trim();
            String field = input.toLowerCase();
            try {
                switch (field) {
                    case "code":
                        String code = readCode();
                        String ownerUsername1 = readOwnerUsername();
                        unitCollectionManager.updateUnitCode(id, code, ownerUsername1);
                        System.out.println("OK");
                        return;

                    case "name":
                        String name = readName();
                        String ownerUsername2 = readOwnerUsername();
                        unitCollectionManager.updateUnitName(id, name, ownerUsername2);
                        System.out.println("OK");
                        return;

                    default: throw new IllegalArgumentException("Данное поле нельзя обновить или его не существует у единицы");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void executeUnitDelete() {
        Long id = readLongID();
        unitCollectionManager.removeUnit(id);
        System.out.println("OK, deleted");
    }

    public void executeConvAdd() {
        System.out.println("Введите короткий код конвертируемой единицы(fromUnitCode): ");
        String fromUnitCode = readFromToCode();
        System.out.println("Введите короткий код единицы, в которую происходит конвертация(toUnitCode): ");
        String toUnitCode = readFromToCode();
        Double factor = readDoubleFactor();
        String ownerUsername = readOwnerUsername();
        ConversionRule conversionRule = conversionRuleCollectionManager.addConversionRule(fromUnitCode, toUnitCode, factor, ownerUsername);
        System.out.println("OK, уникальный номер введённого правила конвертации(ID) = " + conversionRule.getId());

    }

    public void executeConvList() {
        conversionRuleCollectionManager.getAllConversionRules();
    }

    public void executeConvShow() {
        Long id = readLongID();
        conversionRuleCollectionManager.getConversionRuleById(id);
    }

    public void executeConvConvert() {
        Double value = readDoubleValue();
        System.out.println("Введите короткий код конвертируемой единицы(fromCode): ");
        String fromCode = readFromToCode();
        System.out.println("Введите короткий код единицы, в которую происходит конвертация(toCode): ");
        String toCode = readFromToCode();
        ValueWithUnit valueWithUnit = conversionRuleCollectionManager.convert(value, fromCode, toCode);
        System.out.println("Result: " + valueWithUnit.getValue() + " " + valueWithUnit.getUnitCode());
    }

    public void executeConvUpdate() {
        Long id = readLongID();
        Double factor = readDoubleFactor();
        String ownerUsername = readOwnerUsername();
        conversionRuleCollectionManager.updateConversionRule(id, factor, ownerUsername);
        System.out.println("OK");
    }

    public void executeConvCheckCycle() {
        conversionRuleCollectionManager.checkCyclesInConversionRuleCollection();
    }

    private void executeConvDelete() {
        Long id = readLongID();
        conversionRuleCollectionManager.removeConversionRule(id);
        System.out.println("OK, deleted");
    }

    public String readCode() {
        while (true) {
            try {
                System.out.println("Введите короткий код единицы(code): ");
                String code = scanner.nextLine().trim();
                if (unitCollectionManager.getUnitByCode(code) == null)  {
                    validateCode(code);
                    return code;
                } else throw new IllegalArgumentException("Единица с коротким кодом " + code + " уже существует в коллекции");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String readFromToCode() {
        while (true) {
            try {
                String code = scanner.nextLine().trim();
                if (unitCollectionManager.getUnitByCode(code) != null)  {
                    validateCode(code);
                    return code;
                } else throw new IllegalArgumentException("Единица с коротким кодом " + code + " не найдена в коллекции");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String readName() {
        while (true) {
            try {
                System.out.println("Введите человеческое название единицы(name): ");
                String name = scanner.nextLine().trim();
                validateName(name);
                return name;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String readOwnerUsername() {
        while (true) {
            try {
                System.out.println("Введите логин(ownerUsername): ");
                String ownerUsername = scanner.nextLine().trim();
                validateOwnerUsername(ownerUsername);
                return ownerUsername;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Double readDoubleFactor() {
        while (true) {
            System.out.println("Введите коэффицент конвертации:");
            String stringFactor = scanner.nextLine().trim();
            try {
                Double doubleFactor = Double.parseDouble(stringFactor);
                validateFactor(doubleFactor);
                return doubleFactor;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Double readDoubleValue() {
        while (true) {
            System.out.println("Введите величину: ");
            String stringValue = scanner.nextLine().trim();
            try {
                Double doubleValue = Double.parseDouble(stringValue);
                return doubleValue;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Long readLongID() {
        while (true) {
            System.out.println("Введите уникальный номер единицы(ID): ");
            String input = scanner.nextLine().trim();
            try {
                return Long.parseLong(input);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: тип введённого числа не long");
            }
        }
    }
}



