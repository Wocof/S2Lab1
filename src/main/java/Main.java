import cli.ConsoleCLI;
import service.ConversionRuleCollectionManager;
import service.UnitCollectionManager;

public class Main {
    public static void main(String[] args) {
        UnitCollectionManager unitCollectionManager = new UnitCollectionManager();
        ConversionRuleCollectionManager conversionRuleCollectionManager = new ConversionRuleCollectionManager();
        ConsoleCLI cli1 = new ConsoleCLI(unitCollectionManager, conversionRuleCollectionManager);
        cli1.executeCommands();
    }
}
