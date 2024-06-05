import java.util.Arrays; // Импортируем класс Arrays для работы с массивами
import java.util.Random; // Импортируем класс Random для генерации случайных чисел

// Определяем публичный класс SortingComparison
public class SortingComparison {

    // Главный метод, точка входа в программу
    public static void main(String[] args) {
        // Генерируем массивы случайных чисел разного размера
        int[] smallArray = generateRandomArray(1000); // Массив из 1000 элементов
        int[] largeArray = generateRandomArray(100000); // Массив из 100000 элементов

        long startTime, endTime; // Переменные для измерения времени выполнения

        // Пузырьковая сортировка для массива небольшого размера
        startTime = System.currentTimeMillis(); // Фиксируем начальное время
        bubbleSort(smallArray); // Сортируем массив методом пузырьковой сортировки
        endTime = System.currentTimeMillis(); // Фиксируем конечное время
        System.out.println("Пузырьковая сортировка для массива небольшого размера заняла: " + (endTime - startTime) + " мс");

        // Быстрая сортировка для массива небольшого размера
        int[] smallArrayCopy = Arrays.copyOf(smallArray, smallArray.length); // Копируем массив для сортировки другим методом
        startTime = System.currentTimeMillis(); // Фиксируем начальное время
        quickSort(smallArrayCopy, 0, smallArrayCopy.length - 1); // Сортируем массив методом быстрой сортировки
        endTime = System.currentTimeMillis(); // Фиксируем конечное время
        System.out.println("Быстрая сортировка для массива небольшого размера заняла: " + (endTime - startTime) + " мс");

        // Пузырьковая сортировка для массива крупного размера
        startTime = System.currentTimeMillis(); // Фиксируем начальное время
        bubbleSort(largeArray); // Сортируем массив методом пузырьковой сортировки
        endTime = System.currentTimeMillis(); // Фиксируем конечное время
        System.out.println("Пузырьковая сортировка для массива крупного размера заняла: " + (endTime - startTime) + " мс");

        // Быстрая сортировка для массива крупного размера
        int[] largeArrayCopy = Arrays.copyOf(largeArray, largeArray.length); // Копируем массив для сортировки другим методом
        startTime = System.currentTimeMillis(); // Фиксируем начальное время
        quickSort(largeArrayCopy, 0, largeArrayCopy.length - 1); // Сортируем массив методом быстрой сортировки
        endTime = System.currentTimeMillis(); // Фиксируем конечное время
        System.out.println("Быстрая сортировка для массива крупного размера заняла: " + (endTime - startTime) + " мс");
    }

    // Метод для пузырьковой сортировки массива
    public static void bubbleSort(int[] arr) {
        int n = arr.length; // Длина массива
        for (int i = 0; i < n-1; i++) { // Внешний цикл
            for (int j = 0; j < n-i-1; j++) { // Внутренний цикл
                if (arr[j] > arr[j+1]) { // Если текущий элемент больше следующего
                    int temp = arr[j]; // Меняем элементы местами
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    // Метод для быстрой сортировки массива
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) { // Условие выхода из рекурсии
            int pivot = partition(arr, low, high); // Разделяем массив и получаем индекс опорного элемента
            quickSort(arr, low, pivot - 1); // Рекурсивно сортируем левую часть массива
            quickSort(arr, pivot + 1, high); // Рекурсивно сортируем правую часть массива
        }
    }

    // Метод для разделения массива (часть быстрой сортировки)
    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high]; // Выбираем опорный элемент
        int i = low - 1; // Инициализируем индекс меньшего элемента
        for (int j = low; j < high; j++) { // Проходим по массиву
            if (arr[j] < pivot) { // Если текущий элемент меньше опорного
                i++;
                int temp = arr[i]; // Меняем элементы местами
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1]; // Меняем опорный элемент с элементом, который больше опорного
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1; // Возвращаем индекс опорного элемента
    }

    // Метод для генерации массива случайных чисел заданного размера
    public static int[] generateRandomArray(int size) {
        int[] arr = new int[size]; // Создаем массив заданного размера
        Random random = new Random(); // Создаем экземпляр класса Random для генерации случайных чисел
        for (int i = 0; i < size; i++) { // Заполняем массив случайными числами
            arr[i] = random.nextInt(); // Генерируем случайное число и присваиваем его элементу массива
        }
        return arr; // Возвращаем сгенерированный массив
    }
}
