package commands;

import service.ConversionRuleCollectionManager;
import utility.DoubleFactorReader;
import utility.LongIDReader;
import utility.OwnerUsernameReader;

import java.util.NoSuchElementException;

public class ConvUpdate implements ExecuteCommand {

    private final ConversionRuleCollectionManager conversionRuleCollectionManager;
    private final DoubleFactorReader doubleFactorReader;
    private final OwnerUsernameReader ownerUsernameReader;
    private final LongIDReader longIDReader;

    public ConvUpdate(ConversionRuleCollectionManager conversionRuleCollectionManager, DoubleFactorReader doubleFactorReader, OwnerUsernameReader ownerUsernameReader, LongIDReader longIDReader) {
        this.conversionRuleCollectionManager = conversionRuleCollectionManager;
        this.doubleFactorReader = doubleFactorReader;
        this.ownerUsernameReader = ownerUsernameReader;
        this.longIDReader = longIDReader;
    }

    @Override
    public boolean executeCommand() {
        while (true) {
            try {
                Long id = longIDReader.readLongID();
                Double factor = doubleFactorReader.readDoubleFactor();
                String ownerUsername = ownerUsernameReader.readOwnerUsername();
                conversionRuleCollectionManager.updateConversionRule(id, factor, ownerUsername);
                System.out.println("OK");
                break;
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
            }
        }
        return true;
    }
}
