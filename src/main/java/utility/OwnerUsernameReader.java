package utility;

import validation.UnitValidator;

import java.util.Scanner;
import java.util.function.Function;

public class OwnerUsernameReader extends InputReader {


    public OwnerUsernameReader(Scanner scanner) {
        super(scanner);
    }

    public String readOwnerUsername() {
        return readInput(
                "Введите логин(ownerUsername): ",
                Function.identity(),
                UnitValidator::validateOwnerUsername,
                null
        );
    }
}

