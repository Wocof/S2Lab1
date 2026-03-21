package cli;

import commands.*;
import service.ConversionRuleCollectionManager;
import service.UnitCollectionManager;
import utility.*;

import java.util.*;

public class ConsoleCLI {

    private final Scanner scanner;
    private final Map<String, ExecuteCommand> commands = new LinkedHashMap<>();

    public ConsoleCLI(UnitCollectionManager unitCollectionManager, ConversionRuleCollectionManager conversionRuleCollectionManager) {

        this.scanner = new Scanner(System.in);

        CodeReader codeReader = new CodeReader(scanner, unitCollectionManager);
        NameReader nameReader = new NameReader(scanner, unitCollectionManager);
        OwnerUsernameReader ownerUsernameReader = new OwnerUsernameReader(scanner);
        LongIDReader longIDReader = new LongIDReader(scanner);
        FromToCodeReader fromToCodeReader = new FromToCodeReader(scanner, unitCollectionManager);
        DoubleFactorReader doubleFactorReader = new DoubleFactorReader(scanner);
        DoubleValueReader doubleValueReader = new DoubleValueReader(scanner);

        commands.put("help", new Help());
        commands.put("exit", new Exit());
        commands.put("unit_add", new UnitAdd(codeReader, nameReader, ownerUsernameReader, unitCollectionManager));
        commands.put("unit_list", new UnitsList(unitCollectionManager));
        commands.put("unit_show", new UnitShow(longIDReader, unitCollectionManager));
        commands.put("unit_update", new UnitUpdate(longIDReader, codeReader, nameReader,
                ownerUsernameReader, unitCollectionManager));
        commands.put("unit_delete", new UnitDelete(longIDReader, unitCollectionManager));
        commands.put("conv_add", new ConvAdd(fromToCodeReader, doubleFactorReader, ownerUsernameReader,
                conversionRuleCollectionManager));
        commands.put("conv_list", new ConvList(conversionRuleCollectionManager));
        commands.put("conv_show", new ConvShow(longIDReader, conversionRuleCollectionManager));
        commands.put("conv_convert", new ConvConvert(conversionRuleCollectionManager, fromToCodeReader, doubleValueReader));
        commands.put("conv_update", new ConvUpdate(conversionRuleCollectionManager, doubleFactorReader,
                ownerUsernameReader, longIDReader));
        commands.put("conv_check_cycle", new ConvCheckCycle(conversionRuleCollectionManager));
        commands.put("conv_delete", new ConvDelete(conversionRuleCollectionManager, longIDReader));
    }

    public void executeCommands() {
        System.out.println("Справочник единиц и правил их конвертации. Для получения информации о доступных командах введите 'help'.");
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) continue;

            String[] args = input.split("\\s+");
            String commandName = args[0].toLowerCase();

            ExecuteCommand command = commands.get(commandName);
            if (command == null) {
                System.out.println("Неизвестная команда.");
                continue;
            }

            try {
                boolean continueRunning = command.executeCommand();
                if (!continueRunning) {
                    break;
                }
            } catch (IllegalArgumentException | NoSuchElementException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

