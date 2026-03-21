package utility;

import service.UnitCollectionManager;

import java.util.Scanner;
import java.util.function.Function;

import static validation.UnitValidator.validateCode;

public class FromToCodeReader extends InputReader {

    private final UnitCollectionManager unitCollectionManager;

    public FromToCodeReader(Scanner scanner, UnitCollectionManager unitCollectionManager) {
        super(scanner);
        this.unitCollectionManager = unitCollectionManager;
    }

    public String readFromToCode(String initialmessage) {
        return readInput(
                initialmessage,
                Function.identity(),
                code -> { if (unitCollectionManager.getUnitByCode(code) == null)  {
                    throw new IllegalArgumentException("Единица с коротким кодом " + code + " не найдена в коллекции");
                }
                    validateCode(code);},
                null
        );
    }
}
