package utility;

import service.UnitCollectionManager;

import java.util.Scanner;
import java.util.function.Function;

import static validation.UnitValidator.validateName;

public class NameReader extends InputReader {

    private final UnitCollectionManager unitCollectionManager;

    public NameReader(Scanner scanner, UnitCollectionManager unitCollectionManager) {
        super(scanner);
        this.unitCollectionManager = unitCollectionManager;
    }

    public String readName() {
        return readInput(
                "Введите человеческое название единицы(name): ",
                Function.identity(),
                name -> { if (unitCollectionManager.getUnitByName(name) != null)  {
                    throw new IllegalArgumentException("Единица с человеческим названием " + name + " уже существует в коллекции");
                }
                validateName(name);},
                null
        );
    }
}
