import java.util.InputMismatchException;
import java.util.Scanner;

public class LeapYearChecker {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите год: ");
            int year = scanner.nextInt();
            
            if (isLeapYear(year)) {
                System.out.println("Год " + year + " является високосным.");
            } else {
                System.out.println("Год " + year + " не является високосным.");
            }
        } catch (InputMismatchException e) {
            System.err.println("Ошибка: введено некорректное значение. Попробуйте снова.");
        }
    }

    public static boolean isLeapYear(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            return true;
        }
        return false;
    }
}
