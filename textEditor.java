import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TextEditor::new);
    }

    public TextEditor() {
        // Создание главного окна
        JFrame frame = new JFrame("Текстовый редактор");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Создание текстовой области
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Создание строки меню
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Файл");

        // Создание пунктов меню
        JMenuItem openItem = new JMenuItem("Открыть");
        JMenuItem saveItem = new JMenuItem("Сохранить");
        JMenuItem exitItem = new JMenuItem("Выход");

        // Добавление пунктов меню в меню "Файл"
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // Добавление меню "Файл" в строку меню
        menuBar.add(fileMenu);

        // Добавление обработчиков действий
        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showOpenDialog(frame);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        textArea.setText("");
                        String line;
                        while ((line = reader.readLine()) != null) {
                            textArea.append(line + "\n");
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, "Ошибка при открытии файла: " + ex.getMessage(),
                                "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showSaveDialog(frame);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                        writer.write(textArea.getText());
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, "Ошибка при сохранении файла: " + ex.getMessage(),
                                "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        exitItem.addActionListener(e -> System.exit(0));

        // Настройка макета и добавление компонентов
        frame.setJMenuBar(menuBar);
        frame.add(scrollPane);

        // Отображение окна
        frame.setVisible(true);
    }
}
