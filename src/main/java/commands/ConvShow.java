package commands;

import domain.ConversionRule;
import service.ConversionRuleCollectionManager;
import utility.LongIDReader;

import java.util.NoSuchElementException;


public class ConvShow implements ExecuteCommand {

    private final LongIDReader longIDReader;
    private final ConversionRuleCollectionManager conversionRuleCollectionManager;

    public ConvShow(LongIDReader longIDReader, ConversionRuleCollectionManager conversionRuleCollectionManager) {
        this.longIDReader = longIDReader;
        this.conversionRuleCollectionManager = conversionRuleCollectionManager;
    }

    @Override
    public boolean executeCommand() {
        while (true) {
            try {
                Long id = longIDReader.readLongID();
                ConversionRule conversionRule = conversionRuleCollectionManager.getConversionRuleById(id);
                System.out.println("ID: " + conversionRule.getId());
                System.out.println("FromUnitCode: " + conversionRule.getFromUnitCode());
                System.out.println("ToUnitCode: " + conversionRule.getToUnitCode());
                System.out.println("Factor: " + conversionRule.getFactor());
                break;
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
            }
        }
        return true;
    }

}
