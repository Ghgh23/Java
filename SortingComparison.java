import java.util.Arrays;
import java.util.Random;

public class SortingComparison {

    public static void main(String[] args) {
        int[] smallArray = generateRandomArray(1000);
        int[] largeArray = generateRandomArray(100000);

        long startTime, endTime;

        // Пузырьковая сортировка для массива небольшого размера
        startTime = System.currentTimeMillis();
        bubbleSort(smallArray);
        endTime = System.currentTimeMillis();
        System.out.println("Пузырьковая сортировка для массива небольшого размера заняла: " + (endTime - startTime) + " мс");

        // Быстрая сортировка для массива небольшого размера
        int[] smallArrayCopy = Arrays.copyOf(smallArray, smallArray.length); // для сравнения с пузырьковой сортировкой
        startTime = System.currentTimeMillis();
        quickSort(smallArrayCopy, 0, smallArrayCopy.length - 1);
        endTime = System.currentTimeMillis();
        System.out.println("Быстрая сортировка для массива небольшого размера заняла: " + (endTime - startTime) + " мс");

        // Пузырьковая сортировка для массива крупного размера
        startTime = System.currentTimeMillis();
        bubbleSort(largeArray);
        endTime = System.currentTimeMillis();
        System.out.println("Пузырьковая сортировка для массива крупного размера заняла: " + (endTime - startTime) + " мс");

        // Быстрая сортировка для массива крупного размера
        int[] largeArrayCopy = Arrays.copyOf(largeArray, largeArray.length); // для сравнения с пузырьковой сортировкой
        startTime = System.currentTimeMillis();
        quickSort(largeArrayCopy, 0, largeArrayCopy.length - 1);
        endTime = System.currentTimeMillis();
        System.out.println("Быстрая сортировка для массива крупного размера заняла: " + (endTime - startTime) + " мс");
    }

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivot = partition(arr, low, high);
            quickSort(arr, low, pivot - 1);
            quickSort(arr, pivot + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt();
        }
        return arr;
    }
}