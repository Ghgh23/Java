import javax.swing.*; // Импортируем библиотеку для создания графического интерфейса
import java.awt.*;    // Импортируем библиотеку для работы с компоновкой и графикой

// Определяем класс TreeNode, представляющий узел бинарного дерева
class TreeNode {
    int data;          // Поле для хранения данных узла
    TreeNode left;     // Ссылка на левое поддерево
    TreeNode right;    // Ссылка на правое поддерево

    // Конструктор, инициализирующий узел с данными
    TreeNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

// Определяем класс BinaryTree, представляющий бинарное дерево
class BinaryTree {
    public TreeNode root;  // Корневой узел дерева

    // Конструктор, инициализирующий пустое дерево
    BinaryTree() {
        root = null;
    }

    // Метод для вставки нового узла в дерево
    public void insert(int data) {
        root = insertRec(root, data);  // Вызов рекурсивного метода вставки
    }

    // Рекурсивный метод для вставки нового узла в дерево
    private TreeNode insertRec(TreeNode root, int data) {
        if (root == null) {            // Если дерево пустое, создаем новый узел
            root = new TreeNode(data);
            return root;
        }

        if (data < root.data) {        // Если данные меньше текущего узла, идем в левое поддерево
            root.left = insertRec(root.left, data);
        } else if (data > root.data) { // Если данные больше текущего узла, идем в правое поддерево
            root.right = insertRec(root.right, data);
        }

        return root; // Возвращаем текущий узел
    }

    // Реализация других методов работы с бинарным деревом (не включены в данный код)
}

// Определяем класс BinaryTreeGUI для отображения бинарного дерева в графическом интерфейсе
class BinaryTreeGUI extends JFrame {
    private BinaryTree binaryTree; // Экземпляр бинарного дерева
    private JTextArea textArea;    // Текстовое поле для отображения дерева

    // Конструктор, создающий графический интерфейс
    BinaryTreeGUI() {
        binaryTree = new BinaryTree(); // Инициализация бинарного дерева

        // Параметры окна
        setTitle("Binary Tree Visualizer");          // Установка заголовка окна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Закрытие программы при закрытии окна
        setLayout(new BorderLayout());               // Установка компоновки окна
        setSize(400, 300);                           // Установка размера окна

        textArea = new JTextArea();                  // Инициализация текстового поля
        JScrollPane scrollPane = new JScrollPane(textArea); // Добавление прокрутки к текстовому полю

        add(scrollPane, BorderLayout.CENTER);        // Добавление текстового поля с прокруткой в центр окна

        // Пример добавления элементов в бинарное дерево
        binaryTree.insert(5);
        binaryTree.insert(3);
        binaryTree.insert(7);
        binaryTree.insert(2);
        binaryTree.insert(4);

        // Отображение текущего состояния бинарного дерева
        displayTree();
    }

    // Метод для отображения бинарного дерева в текстовом поле
    private void displayTree() {
        textArea.setText("");                // Очистка текстового поля
        displayTree(binaryTree.root, 0);     // Вызов рекурсивного метода отображения дерева
    }

    // Рекурсивный метод для отображения бинарного дерева
    private void displayTree(TreeNode root, int level) {
        if (root != null) {
            displayTree(root.right, level + 1); // Отображаем правое поддерево, увеличивая уровень
            for (int i = 0; i < level; i++) {
                textArea.append("\t");           // Добавляем отступы для визуализации уровня дерева
            }
            textArea.append(root.data + "\n");  // Отображаем данные текущего узла
            displayTree(root.left, level + 1);  // Отображаем левое поддерево, увеличивая уровень
        }
    }

    // Главный метод для запуска программы
    public static void main(String[] args) {
        BinaryTreeGUI gui = new BinaryTreeGUI(); // Создаем экземпляр графического интерфейса
        gui.setVisible(true);                    // Делаем окно видимым
    }
}
