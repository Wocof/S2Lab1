package commands;

import domain.ValueWithUnit;
import service.ConversionRuleCollectionManager;
import utility.DoubleValueReader;
import utility.FromToCodeReader;

import java.util.NoSuchElementException;

public class ConvConvert implements ExecuteCommand {

    private final ConversionRuleCollectionManager conversionRuleCollectionManager;
    private final FromToCodeReader fromToCodeReader;
    private final DoubleValueReader doubleValueReader;

    public ConvConvert(ConversionRuleCollectionManager conversionRuleCollectionManager, FromToCodeReader fromToCodeReader, DoubleValueReader doubleValueReader) {
        this.conversionRuleCollectionManager = conversionRuleCollectionManager;
        this.fromToCodeReader = fromToCodeReader;
        this.doubleValueReader = doubleValueReader;
    }

    @Override
    public boolean executeCommand() {
        while (true) {
            try {
                Double value = doubleValueReader.readDoubleValue();
                String fromCode = fromToCodeReader.readFromToCode("Введите короткий код конвертируемой единицы(fromCode): ");
                String toCode = fromToCodeReader.readFromToCode("Введите короткий код единицы, в которую происходит конвертация(toCode): ");
                ValueWithUnit valueWithUnit = conversionRuleCollectionManager.convert(value, fromCode, toCode);
                System.out.println("Result: " + valueWithUnit.getValue() + " " + valueWithUnit.getUnitCode());
                break;
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
            }
        }
        return true;
    }
}
