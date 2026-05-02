package storage;

import domain.ConversionRule;
import domain.Unit;
import java.util.*;

import static validation.ConversionRuleValidator.*;
import static validation.UnitValidator.*;

public class FileValidator {

    public static List<String> validateFile(StorageData storageData) {

        List<String> errors = new ArrayList<>();

        if (storageData == null) {
            errors.add("Файл не содержит данных (null)");
            return errors;
        }

        //Проверка полей Unit
        Set<Long> unitIds = new HashSet<>();
        Set<String> unitCodes = new HashSet<>();
        if (storageData.getUnits() != null) {
            for (Unit unit : storageData.getUnits()) {
                //Уникальность id
                if (!unitIds.add(unit.getId())) {
                    errors.add("Единица измерения с уникальным номером(id) " + unit.getId() + " уже есть в коллекции");
                }
                //Уникальность code
                if (unit.getCode() != null && !unitCodes.add(unit.getCode())) {
                    errors.add("Единица измерения с коротким кодом(code)" + unit.getCode() + " уже есть в коллекции");
                }
                //Ограничения полей
                try {
                    validateUnit(unit);
                } catch (IllegalArgumentException e) {
                    errors.add("Ошибка в единице измерения с уникальным номером(id) " + unit.getId() + ": " + e.getMessage());
                }
            }
        }

        //Проверка полей ConversionRule
        Set<Long> conversionRuleIds = new HashSet<>();
        if (storageData.getConversionRules() != null) {
            for (ConversionRule conversionRule : storageData.getConversionRules()) {
                //Уникальность id
                if (!conversionRuleIds.add(conversionRule.getId())) {
                    errors.add("Правило конвертации с уникальным номером(id) " + conversionRule.getId() + " уже есть в коллекции");
                }
                //Ограричения полей
                try {
                    validateConversionRule(conversionRule);
                    validateOwnerUsername(conversionRule.getOwnerUsername());
                } catch (IllegalArgumentException e) {
                    errors.add("Ошибка в правиле конвертации с уникальным номером(id) " + conversionRule.getId() + ": " + e.getMessage());
                }
                //Целостность ссылок
                if (conversionRule.getFromUnitCode() != null && !unitCodes.contains(conversionRule.getFromUnitCode())) {
                    errors.add("Правило конвертации с уникальным номером(id) " + conversionRule.getId() + " ссылается на несуществующую единицу fromUnitCode " + conversionRule.getFromUnitCode());
                }
                if (conversionRule.getToUnitCode() != null && !unitCodes.contains(conversionRule.getToUnitCode())) {
                    errors.add("Правило конвертации с уникальным номером(id) " + conversionRule.getId() + " ссылается на несуществующую единицу toUnitCode" + conversionRule.getToUnitCode());
                }
            }
        }
        return errors;
    }
}
