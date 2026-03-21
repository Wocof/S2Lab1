package utility;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class InputReader {

    protected final Scanner scanner;

    public InputReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public <T> T readInput(String initialMessage, Function<String, T> parser, Consumer<T> validator, String numberFormatExceptionMessage) {
        while (true) {
            System.out.print(initialMessage);
            String input = scanner.nextLine().trim();
            try {
                T value = parser.apply(input);
                if (validator != null) validator.accept(value);
                return value;
            } catch (NumberFormatException e) {
                if (numberFormatExceptionMessage != null) {
                    System.out.println(numberFormatExceptionMessage);
                } else {
                    System.out.println(e.getMessage());
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
