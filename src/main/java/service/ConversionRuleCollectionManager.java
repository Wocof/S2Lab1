package service;

import domain.ConversionRule;
import domain.ValueWithUnit;

import java.time.Instant;
import java.util.*;

import static validation.ConversionRuleValidator.*;


public class ConversionRuleCollectionManager {

    private final Map<Long, ConversionRule> conversionRuleCollection = new LinkedHashMap<>();

    private long getConversionRuleNextId() {
        return System.currentTimeMillis() + conversionRuleCollection.size();
    }

    public ConversionRule addConversionRule(String fromUnitCode, String toUnitCode, double factor, String ownerUsername) {
        long id = getConversionRuleNextId();
        ConversionRule conversionRule = new ConversionRule(id, fromUnitCode, toUnitCode, factor, ownerUsername, Instant.now(), Instant.now());
        validateConversionRule(conversionRule);
        conversionRuleCollection.put(conversionRule.getId(), conversionRule);
        return conversionRule;
    }

    public void getConversionRuleById(long id) {
        ConversionRule conversionRule = conversionRuleCollection.get(id);
        if (conversionRule != null) {
            System.out.println("ID: " + conversionRule.getId());
            System.out.println("FromUnitCode: " + conversionRule.getFromUnitCode());
            System.out.println("ToUnitCode: " + conversionRule.getToUnitCode());
            System.out.println("Factor: " + conversionRule.getFactor());
        } else throw new NoSuchElementException("Правило конвертации с таким уникальным номером(ID) не найдено");
    }

    public void getAllConversionRules() {
        System.out.printf("%-15s %-10s %-10s %-10s%n", "ID", "From", "To", "Factor");
        for (ConversionRule conversionRule : conversionRuleCollection.values()) {
            System.out.printf("%-15d %-10s %-10s %-10f%n",
                    conversionRule.getId(),
                    conversionRule.getFromUnitCode(),
                    conversionRule.getToUnitCode(),
                    conversionRule.getFactor());
        }
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

    public void checkCyclesInConversionRuleCollection() {
        boolean found = false;
        for(ConversionRule conversionRule1: conversionRuleCollection.values()) {
            for(ConversionRule conversionRule2: conversionRuleCollection.values()) {
                if(conversionRule1.getFromUnitCode().equals(conversionRule2.getToUnitCode()) && conversionRule2.getFromUnitCode().equals(conversionRule1.getToUnitCode()) && Math.abs(conversionRule1.getFactor() * conversionRule2.getFactor() - 1) > 0.00001) {
                    System.out.println("Найден подозрительный цикл " + conversionRule1 + conversionRule2);
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("Подозрительные циклы не найдены");
        }
    }
}

