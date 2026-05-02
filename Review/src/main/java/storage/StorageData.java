package storage;

import domain.ConversionRule;
import domain.Unit;

import java.io.Serializable;
import java.util.List;

public class StorageData implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Unit> units;
    private List<ConversionRule> conversionRules;

    public StorageData(List<Unit> units, List<ConversionRule> conversionRules) {
        this.units = units;
        this.conversionRules = conversionRules;
    }

    public List<Unit> getUnits() {
        return units;
    }

    public void setUnits(List<Unit> units) {
        this.units = units;
    }

    public List<ConversionRule> getConversionRules() {
        return conversionRules;
    }

    public void setConversionRules(List<ConversionRule> conversionRules) {
        this.conversionRules = conversionRules;
    }
}
