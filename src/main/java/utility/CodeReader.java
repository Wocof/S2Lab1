package utility;

import service.UnitCollectionManager;

import java.util.Scanner;
import java.util.function.Function;

import static validation.UnitValidator.validateCode;

public class CodeReader extends InputReader {

    private final UnitCollectionManager unitCollectionManager;

    public CodeReader(Scanner scanner, UnitCollectionManager unitCollectionManager) {
        super(scanner);
        this.unitCollectionManager = unitCollectionManager;
    }

    public String readCode() {
        return readInput(
                "Введите короткий код единицы(code): ",
                Function.identity(),
                code -> { if (unitCollectionManager.getUnitByCode(code) != null)  {
                    throw new IllegalArgumentException("Единица с коротким кодом " + code + " уже существует в коллекции");
                }
                validateCode(code);},
                null
                );
    }
}
