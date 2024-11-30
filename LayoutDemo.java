import javax.swing.*;
import java.awt.*;

public class LayoutDemo {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LayoutDemo::new);
    }

    public LayoutDemo() {
        // Создание главного окна
        JFrame frame = new JFrame("Layout Manager Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Установка BorderLayout для основного окна
        frame.setLayout(new BorderLayout());

        // Добавление кнопок в разные зоны BorderLayout
        frame.add(new JButton("Север"), BorderLayout.NORTH);
        frame.add(new JButton("Юг"), BorderLayout.SOUTH);
        frame.add(new JButton("Запад"), BorderLayout.WEST);
        frame.add(new JButton("Восток"), BorderLayout.EAST);

        // Создание панели с FlowLayout
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Добавление кнопок на панель с FlowLayout
        for (int i = 1; i <= 5; i++) {
            centerPanel.add(new JButton("Кнопка " + i));
        }

        // Добавление панели в центр BorderLayout
        frame.add(centerPanel, BorderLayout.CENTER);

        // Отображение окна
        frame.setVisible(true);
    }
}
