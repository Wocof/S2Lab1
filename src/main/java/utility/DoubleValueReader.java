package utility;

import java.util.Scanner;

public class DoubleValueReader extends InputReader {

    public DoubleValueReader(Scanner scanner) {
        super(scanner);
    }

    public Double readDoubleValue() {
        return readInput(
                "Введите величину(value): ",
                Double::parseDouble,
                null,
                "Введённая величина(value) должна иметь тип double"
        );
    }
}
