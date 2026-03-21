package utility;

import java.util.Scanner;

public class LongIDReader extends InputReader {

    public LongIDReader(Scanner scanner) {
        super(scanner);
    }

    public Long readLongID() {
        return readInput(
                "Введите уникальный номер единицы(ID): ",
                Long::parseLong,
                null,
                "Ошибка: тип введённого числа не long"
        );
    }
}
