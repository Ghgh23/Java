public class Stack { // Определение класса Stack (стек)
    private int mSize; // Поле для хранения размера стека
    private int [] stackArray; // Массив для хранения элементов стека
    private int top; // Индекс вершины стека

    // Конструктор класса Stack, принимающий размер стека в качестве параметра
    public Stack(int m){
        this.mSize = m; // Инициализация размера стека
        stackArray = new int[mSize]; // Создание массива для хранения элементов стека
        top = -1; // Инициализация индекса вершины стека (стек пуст)
    }

    // Метод для добавления элемента в стек
    public void addElement(int element){
        stackArray[++top] = element; // Увеличиваем индекс вершины и добавляем элемент в стек
    }

    // Метод для удаления элемента из стека
    public int deleteElement(){
        return stackArray[top--]; // Возвращаем элемент с вершины стека и уменьшаем индекс вершины
    }

    // Метод для чтения элемента с вершины стека, не удаляя его
    public int readTop(){
        return stackArray[top]; // Возвращаем элемент с вершины стека
    }

    // Метод для проверки, пуст ли стек
    public boolean isEmpty(){
        return (top == -1); // Возвращаем true, если индекс вершины равен -1 (стек пуст)
    }

    // Метод для проверки, полон ли стек
    public boolean isFull(){
        return (top == mSize - 1); // Возвращаем true, если индекс вершины равен размеру стека минус один (стек полон)
    }
}

// Определение класса AwesomeStack для тестирования работы стека
class AwesomeStack {
    // Главный метод, точка входа в программу
    public static void main(String[] args){
        Stack mStack = new Stack(10); // Создаем стек размером 10 элементов

        mStack.addElement(23); // Добавляем элементы в стек
        mStack.addElement(45);
        mStack.addElement(3);
        mStack.addElement(53);

        mStack.deleteElement(); // Удаляем элемент с вершины стека

        System.out.println("Стек: "); // Выводим сообщение

        // Удаляем и выводим все элементы из стека, пока он не станет пустым
        while (!mStack.isEmpty()){
            int value = mStack.deleteElement(); // Удаляем элемент с вершины стека
            System.out.println(value); // Выводим удаленный элемент
        }
    }
}
