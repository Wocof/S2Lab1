import cli.ConsoleCLI;
import commands.Load;
import service.ConversionRuleCollectionManager;
import service.UnitCollectionManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        UnitCollectionManager unitCollectionManager = new UnitCollectionManager();
        ConversionRuleCollectionManager conversionRuleCollectionManager = new ConversionRuleCollectionManager();

        if (args.length > 0) {
            String filePath = args[0];
            Load loadCommand = new Load(unitCollectionManager, conversionRuleCollectionManager, new Scanner(System.in));
            loadCommand.executeCommand(new String[]{filePath});
        }

        ConsoleCLI cli1 = new ConsoleCLI(unitCollectionManager, conversionRuleCollectionManager);
        cli1.executeCommands();
    }
}
