import java.util.Scanner;

public class CompoundInterestCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите начальную сумму: ");
        double initialAmount = scanner.nextDouble();

        System.out.print("Введите годовую процентную ставку (в процентах): ");
        double interestRate = scanner.nextDouble() / 100;

        System.out.print("Введите количество периодов: ");
        int periods = scanner.nextInt();

        // Расчёт сложного процента
        double finalAmount = calculateCompoundInterest(initialAmount, interestRate, periods);
        System.out.printf("Итоговая сумма после %d периодов составит %.2f\n", periods, finalAmount);

        // Обратное действие: расчёт процентной ставки
        System.out.print("\nВведите конечную сумму: ");
        double targetAmount = scanner.nextDouble();

        double requiredRate = findRequiredInterestRate(initialAmount, targetAmount, periods);
        System.out.printf("Необходимая процентная ставка составляет %.2f%%\n", requiredRate * 100);

        scanner.close();
    }

 
     @param principal   Начальная сумма
     @param rate        Годовая процентная ставка (в десятичном формате)
     @param periods     Количество периодов
     @return Итоговая сумма после указанного количества периодов

    public static double calculateCompoundInterest(double principal, double rate, int periods) {
        return principal * Math.pow(1 + rate, periods);
    }

 
      @param principal   Начальная сумма
      @param target      Целевая сумма
      @param periods     Количество периодов
      @return Необходимая годовая процентная ставка (в десятичном формате)
 
    public static double findRequiredInterestRate(double principal, double target, int periods) {
        if (periods == 0 || principal == 0) {
            return 0;
        }
        return Math.pow(target / principal, 1.0 / periods) - 1;
    }
}
