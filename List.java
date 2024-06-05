import javax.swing.*; // Импортируем библиотеку для создания графического интерфейса
import java.awt.BorderLayout; // Импортируем класс для работы с компоновкой BorderLayout
import java.awt.event.ActionEvent; // Импортируем класс для обработки событий ActionEvent
import java.awt.event.ActionListener; // Импортируем интерфейс для создания слушателей событий

// Определяем класс List, наследующий JFrame для создания графического интерфейса
public class List extends JFrame {
    private DefaultListModel<String> listModel; // Модель списка для хранения элементов
    private JList<String> list; // Визуальный компонент для отображения элементов списка
    private JTextField textField; // Поле ввода для добавления новых элементов

    // Конструктор класса List
    public List() {
        setTitle("Список с графическим интерфейсом"); // Устанавливаем заголовок окна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Устанавливаем поведение при закрытии окна
        setSize(300, 200); // Устанавливаем размер окна

        // Создание модели списка и компонента списка
        listModel = new DefaultListModel<>(); // Инициализируем модель списка
        list = new JList<>(listModel); // Инициализируем компонент списка с моделью
        JScrollPane scrollPane = new JScrollPane(list); // Добавляем компонент списка в прокручивающую панель
        add(scrollPane); // Добавляем прокручивающую панель в окно

        // Создание текстового поля для ввода текста
        textField = new JTextField(); // Инициализируем текстовое поле
        add(textField, BorderLayout.NORTH); // Добавляем текстовое поле в верхнюю часть окна

        // Создание кнопки для добавления элемента в список
        JButton addButton = new JButton("Добавить"); // Инициализируем кнопку
        addButton.addActionListener(new ActionListener() { // Добавляем слушатель событий для кнопки
            @Override
            public void actionPerformed(ActionEvent e) { // Метод, вызываемый при нажатии на кнопку
                String text = textField.getText(); // Получаем текст из текстового поля
                if (!text.isEmpty()) { // Проверяем, что текстовое поле не пустое
                    listModel.addElement(text); // Добавляем текст в модель списка
                    textField.setText(""); // Очищаем текстовое поле
                }
            }
        });

        // Создание кнопки для удаления выбранного элемента из списка
        JButton removeButton = new JButton("Удалить"); // Инициализируем кнопку
        removeButton.addActionListener(new ActionListener() { // Добавляем слушатель событий для кнопки
            @Override
            public void actionPerformed(ActionEvent e) { // Метод, вызываемый при нажатии на кнопку
                int selectedIndex = list.getSelectedIndex(); // Получаем индекс выбранного элемента
                if (selectedIndex != -1) { // Проверяем, что элемент выбран
                    listModel.remove(selectedIndex); // Удаляем выбранный элемент из модели списка
                }
            }
        });

        // Создание панели для кнопок
        JPanel buttonPanel = new JPanel(); // Инициализируем панель
        buttonPanel.add(addButton); // Добавляем кнопку добавления на панель
        buttonPanel.add(removeButton); // Добавляем кнопку удаления на панель

        add(buttonPanel, BorderLayout.SOUTH); // Добавляем панель кнопок в нижнюю часть окна

        setVisible(true); // Делаем окно видимым
    }

    // Главный метод для запуска программы
    public static void main(String[] args) {
        List guiList = new List(); // Создаем экземпляр графического интерфейса
    }
}
