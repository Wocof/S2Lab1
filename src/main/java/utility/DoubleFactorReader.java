package utility;

import validation.ConversionRuleValidator;

import java.util.Scanner;

public class DoubleFactorReader extends InputReader {

    public DoubleFactorReader(Scanner scanner) {
        super(scanner);
    }

    public Double readDoubleFactor() {
        return readInput(
                "Введите коэффицент конвертации(factor): ",
                Double::parseDouble,
                ConversionRuleValidator::validateFactor,
                "Введённый коэффицент конвертации(factor) должен иметь тип double"
        );
    }
}

