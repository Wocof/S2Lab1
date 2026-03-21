package commands;

import domain.ConversionRule;
import service.ConversionRuleCollectionManager;


import java.util.Collection;

public class ConvList implements ExecuteCommand {

    private final ConversionRuleCollectionManager conversionRuleCollectionManager;

    public ConvList(ConversionRuleCollectionManager conversionRuleCollectionManager) {
        this.conversionRuleCollectionManager = conversionRuleCollectionManager;
    }

    @Override
    public boolean executeCommand() {
        Collection<ConversionRule> conversionRules = conversionRuleCollectionManager.getAllConversionRules();
        System.out.printf("%-15s %-10s %-10s %-10s%n", "ID", "From", "To", "Factor");
        for (ConversionRule conversionRule : conversionRules) {
            System.out.printf("%-15d %-10s %-10s %-10f%n",
                    conversionRule.getId(),
                    conversionRule.getFromUnitCode(),
                    conversionRule.getToUnitCode(),
                    conversionRule.getFactor());
        }
        return true;
    }
}
