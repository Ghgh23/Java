import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();

        // Определяем оператор и выполняем соответствующую операцию
        char operator = getOperator(input);

        if (operator == '\0') {
            System.out.println("Некорректное выражение");
            return;
        }

        String[] operands = input.split("\\" + operator);
        if (operands.length != 2) {
            System.out.println("Некорректное выражение");
            return;
        }

        try {
            int a = Integer.parseInt(operands[0].trim());
            int b = Integer.parseInt(operands[1].trim());

            // Вычисляем результат
            int result = calculate(a, b, operator);
            System.out.println(result);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введены некорректные числа.");
        } catch (ArithmeticException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static char getOperator(String input) {
        // Возвращает первый найденный оператор
        for (char c : new char[]{'+', '-', '*', '/'}) {
            if (input.contains(String.valueOf(c))) {
                return c;
            }
        }
        return '\0'; // Нет оператора
    }

    private static int calculate(int a, int b, char operator) {
        // Выполняет вычисления в зависимости от оператора
        return switch (operator) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> {
                if (b == 0) throw new ArithmeticException("деление на ноль");
                yield a / b;
            }
            default -> throw new IllegalArgumentException("Неизвестный оператор: " + operator);
        };
    }
}
