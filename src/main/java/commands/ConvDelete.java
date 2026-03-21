package commands;

import service.ConversionRuleCollectionManager;
import utility.LongIDReader;

import java.util.NoSuchElementException;

public class ConvDelete implements ExecuteCommand {

    private final ConversionRuleCollectionManager conversionRuleCollectionManager;
    private final LongIDReader longIDReader;

    public ConvDelete(ConversionRuleCollectionManager conversionRuleCollectionManager, LongIDReader longIDReader) {
        this.conversionRuleCollectionManager = conversionRuleCollectionManager;
        this.longIDReader = longIDReader;
    }

    @Override
    public boolean executeCommand() {

        while (true) {
            try {
                Long id = longIDReader.readLongID();
                conversionRuleCollectionManager.removeConversionRule(id);
                System.out.println("OK, deleted");
                break;
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
            }
        }
        return true;
    }
}

