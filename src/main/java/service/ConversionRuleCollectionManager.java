package service;

import domain.ConversionRule;
import domain.ValueWithUnit;
import utility.IdGenerator;

import java.time.Instant;
import java.util.*;

import static validation.ConversionRuleValidator.*;


public class ConversionRuleCollectionManager {

    private final Map<Long, ConversionRule> conversionRuleCollection = new LinkedHashMap<>();

    public ConversionRule addConversionRule(String fromUnitCode, String toUnitCode, double factor, String ownerUsername) {
        long id = IdGenerator.generateID(conversionRuleCollection.size());
        ConversionRule conversionRule = new ConversionRule(id, fromUnitCode, toUnitCode, factor, ownerUsername, Instant.now(), Instant.now());
        validateConversionRule(conversionRule);
        conversionRuleCollection.put(conversionRule.getId(), conversionRule);
        return conversionRule;
    }

    public ConversionRule getConversionRuleById(long id) {
        ConversionRule conversionRule = conversionRuleCollection.get(id);
        if (conversionRule != null) {
            return conversionRule;
        } else throw new NoSuchElementException("Правило конвертации с таким уникальным номером(ID) не найдено");
    }

    public Collection<ConversionRule> getAllConversionRules() {
        return conversionRuleCollection.values();
    }

    public void updateConversionRule(long id, double factor, String ownerUsername) {
        ConversionRule conversionRule = conversionRuleCollection.get(id);
        if (conversionRule != null) {
            conversionRule.setFactor(factor);
            conversionRule.setOwnerUsername(ownerUsername);
            conversionRule.setUpdatedAt(Instant.now());
            validateConversionRule(conversionRule);
        } else throw new NoSuchElementException("Правило конвертации с таким уникальным номером(ID) не найдено");
    }

    public void removeConversionRule(long id) {
        ConversionRule conversionRule = conversionRuleCollection.get(id);
        if (conversionRule != null) {
            conversionRuleCollection.remove(id);
        } else throw new NoSuchElementException("Правило конвертации с таким уникальным номером(ID) не найдено");
    }

    public ValueWithUnit convert(double value, String fromCode, String toCode) {

        validateFromUnitCode(fromCode);
        validateToUnitCode(toCode);

        for(ConversionRule conversionRule: conversionRuleCollection.values()) {
            if (conversionRule.getFromUnitCode().equals(fromCode) && conversionRule.getToUnitCode().equals(toCode)) {
                double resultvalue =  value * conversionRule.getFactor();
                return new ValueWithUnit(resultvalue, toCode);
            }
        }
        throw new NoSuchElementException("Правило конвертации из " + fromCode + " в " + toCode + " не найдено" );
    }

    public String checkCyclesInConversionRuleCollection() {
        boolean found = false;
        for(ConversionRule conversionRule1: conversionRuleCollection.values()) {
            for(ConversionRule conversionRule2: conversionRuleCollection.values()) {
                if(conversionRule1.getFromUnitCode().equals(conversionRule2.getToUnitCode()) && conversionRule2.getFromUnitCode().equals(conversionRule1.getToUnitCode()) && Math.abs(conversionRule1.getFactor() * conversionRule2.getFactor() - 1) > 0.00001) {
                    return "Найден подозрительный цикл " + conversionRule1 + conversionRule2;
                }
            }
        }
        return "Подозрительные циклы не найдены";
    }
}
