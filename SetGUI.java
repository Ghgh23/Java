import javax.swing.*; // Импортируем библиотеку для создания графического интерфейса
import java.awt.*; // Импортируем библиотеку для работы с компоновкой и графикой
import java.awt.event.ActionEvent; // Импортируем класс для обработки событий ActionEvent
import java.awt.event.ActionListener; // Импортируем интерфейс для создания слушателей событий
import java.util.HashSet; // Импортируем класс HashSet для использования множества
import java.util.Set; // Импортируем интерфейс Set

// Определяем класс SetGUI, наследующий JFrame для создания графического интерфейса
public class SetGUI extends JFrame {
    private Set<Integer> set; // Множество для хранения уникальных элементов
    private JTextArea textArea; // Текстовое поле для отображения элементов множества

    // Конструктор класса SetGUI
    public SetGUI() {
        set = new HashSet<>(); // Инициализируем множество
        textArea = new JTextArea(10, 20); // Инициализируем текстовое поле с размерами 10x20

        JTextField inputField = new JTextField(10); // Создаем текстовое поле для ввода с шириной 10 символов

        JButton addButton = new JButton("Добавить элемент"); // Создаем кнопку для добавления элемента
        addButton.addActionListener(new ActionListener() { // Добавляем слушатель событий для кнопки
            @Override
            public void actionPerformed(ActionEvent e) { // Метод, вызываемый при нажатии на кнопку
                try {
                    int value = Integer.parseInt(inputField.getText()); // Преобразуем текстовое поле в целое число
                    set.add(value); // Добавляем число в множество
                    updateSet(); // Обновляем отображение множества
                } catch (NumberFormatException ex) { // Обработка исключения при неверном формате ввода
                    ex.printStackTrace(); // Выводим стек трассировки исключения
                    JOptionPane.showMessageDialog(null, "Пожалуйста, введите целое число."); // Отображаем сообщение об ошибке
                }
            }
        });

        JButton removeButton = new JButton("Удалить элемент"); // Создаем кнопку для удаления элемента
        removeButton.addActionListener(new ActionListener() { // Добавляем слушатель событий для кнопки
            @Override
            public void actionPerformed(ActionEvent e) { // Метод, вызываемый при нажатии на кнопку
                try {
                    int value = Integer.parseInt(inputField.getText()); // Преобразуем текстовое поле в целое число
                    set.remove(value); // Удаляем число из множества
                    updateSet(); // Обновляем отображение множества
                } catch (NumberFormatException ex) { // Обработка исключения при неверном формате ввода
                    ex.printStackTrace(); // Выводим стек трассировки исключения
                    JOptionPane.showMessageDialog(null, "Пожалуйста, введите целое число."); // Отображаем сообщение об ошибке
                }
            }
        });

        JPanel panel = new JPanel(); // Создаем панель для размещения компонентов
        panel.add(inputField); // Добавляем текстовое поле на панель
        panel.add(addButton); // Добавляем кнопку добавления на панель
        panel.add(removeButton); // Добавляем кнопку удаления на панель

        add(panel, BorderLayout.NORTH); // Добавляем панель в верхнюю часть окна
        add(new JScrollPane(textArea), BorderLayout.CENTER); // Добавляем текстовое поле с прокруткой в центр окна

        setTitle("Множество"); // Устанавливаем заголовок окна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Устанавливаем поведение при закрытии окна
        pack(); // Автоматически подбираем оптимальный размер окна
        setLocationRelativeTo(null); // Размещаем окно в центре экрана
        setVisible(true); // Делаем окно видимым
    }

    // Метод для обновления отображения множества в текстовом поле
    private void updateSet() {
        textArea.setText(""); // Очищаем текстовое поле
        for (int element : set) { // Проходим по всем элементам множества
            textArea.append(element + "\n"); // Добавляем каждый элемент в текстовое поле
        }
    }

    // Главный метод для запуска программы
    public static void main(String[] args) {
        SwingUtilities.invokeLater(SetGUI::new); // Создаем и отображаем экземпляр SetGUI в потоке обработки событий
    }
}
