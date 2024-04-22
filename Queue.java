public class Queue {
    private int maxSize;
    private int[] queueArray;
    private int front;
    private int rear;
    private int nItems;

    public Queue(int size) {
        maxSize = size;
        queueArray = new int[maxSize];
        front = 0;
        rear = -1;
        nItems = 0;
    }

    public void insert(int value) {
        if (rear == maxSize - 1) {
            rear = -1;
        }
        queueArray[++rear] = value;
        nItems++;
    }

    public int remove() {
        int temp = queueArray[front++];
        if (front == maxSize) {
            front = 0;
        }
        nItems--;
        return temp;
    }

    public int peekFront() {
        return queueArray[front];
    }

    public boolean isEmpty() {
        return (nItems == 0);
    }

    public boolean isFull() {
        return (nItems == maxSize);
    }

    public int size() {
        return nItems;
    }
}
class Main {
    public static void main(String[] args) {
        Queue myQueue = new Queue(5);

        myQueue.insert(10);
        myQueue.insert(20);
        myQueue.insert(30);
        myQueue.insert(40);

        System.out.println("Проверка наличия элементов в очереди: " + myQueue.isEmpty());
        System.out.println("Размер очереди: " + myQueue.size());
        System.out.println("Первый элемент в очереди: " + myQueue.peekFront());
        System.out.println("Извлеченный элемент из очереди: " + myQueue.remove());
    }
}