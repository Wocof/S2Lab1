package commands;

import domain.ConversionRule;
import service.ConversionRuleCollectionManager;
import utility.DoubleFactorReader;
import utility.FromToCodeReader;
import utility.OwnerUsernameReader;

public class ConvAdd implements ExecuteCommand {

    private final FromToCodeReader fromToCodeReader;
    private final DoubleFactorReader doubleFactorReader;
    private final OwnerUsernameReader ownerUsernameReader;
    private final ConversionRuleCollectionManager conversionRuleCollectionManager;

    public ConvAdd(FromToCodeReader fromToCodeReader, DoubleFactorReader doubleFactorReader, OwnerUsernameReader ownerUsernameReader, ConversionRuleCollectionManager conversionRuleCollectionManager) {
        this.fromToCodeReader = fromToCodeReader;
        this.doubleFactorReader = doubleFactorReader;
        this.ownerUsernameReader = ownerUsernameReader;
        this.conversionRuleCollectionManager = conversionRuleCollectionManager;
    }

    @Override
    public boolean executeCommand() {
        String fromUnitCode = fromToCodeReader.readFromToCode("Введите короткий код конвертируемой единицы(fromUnitCode): ");
        String toUnitCode = fromToCodeReader.readFromToCode("Введите короткий код единицы, в которую происходит конвертация(toUnitCode): ");
        Double factor = doubleFactorReader.readDoubleFactor();
        String ownerUsername = ownerUsernameReader.readOwnerUsername();
        ConversionRule conversionRule = conversionRuleCollectionManager.addConversionRule(fromUnitCode, toUnitCode, factor, ownerUsername);
        System.out.println("OK, уникальный номер введённого правила конвертации(ID) = " + conversionRule.getId());
        return true;
    }
}
