package validation;

import domain.ConversionRule;

public class ConversionRuleValidator {

    public static void validateFromUnitCode(String fromUnitCode) {
        if (fromUnitCode == null) {
            throw new IllegalArgumentException("Короткий код конвертируемой единицы(fromUnitCode) не может быть null");
        }
        if (fromUnitCode.isEmpty()) {
            throw new IllegalArgumentException("Короткий код конвертируемой единицы(fromUnitCode) не может быть пустым");
        }
    }

    public static void validateToUnitCode(String toUnitCode) {
        if (toUnitCode == null) {
            throw new IllegalArgumentException("Короткий код единицы, в которую происходит конвертация(toUnitCode), не может быть null");
        }
        if (toUnitCode.isEmpty()) {
            throw new IllegalArgumentException("Короткий код единицы, в которую происходит конвертация(toUnitCode), не может быть пустым");
        }
    }

    public static void validateFactor(double factor) {
        if (factor <= 0) {
            throw new IllegalArgumentException("Коэффициент конвертации(factor) должен быть больше 0");
        }
    }

    public static void validateConversionRule(ConversionRule conversionRule) {
        validateFromUnitCode(conversionRule.getFromUnitCode());
        validateToUnitCode(conversionRule.getToUnitCode());
        validateFactor(conversionRule.getFactor());
    }
}
