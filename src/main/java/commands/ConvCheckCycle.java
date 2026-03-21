package commands;

import service.ConversionRuleCollectionManager;

public class ConvCheckCycle implements ExecuteCommand {

    private final ConversionRuleCollectionManager conversionRuleCollectionManager;

    public ConvCheckCycle(ConversionRuleCollectionManager conversionRuleCollectionManager) {
        this.conversionRuleCollectionManager = conversionRuleCollectionManager;
    }

    @Override
    public boolean executeCommand() {
        conversionRuleCollectionManager.checkCyclesInConversionRuleCollection();
        return true;
    }
}
