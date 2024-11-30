import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AutoCorrectTextEditor {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(AutoCorrectTextEditor::new);
    }

    public AutoCorrectTextEditor() {
        // Создание главного окна
        JFrame frame = new JFrame("Текстовый редактор с автозаменой");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Создание текстовой области
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Словарь для автозамены
        Map<String, String> autoCorrectMap = new HashMap<>();
        autoCorrectMap.put("teh", "the");
        autoCorrectMap.put("recieve", "receive");
        autoCorrectMap.put("adress", "address");

        // Поток для выполнения автозамены
        Thread autoCorrectThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(100); // Небольшая задержка
                    SwingUtilities.invokeLater(() -> performAutoCorrect(textArea, autoCorrectMap));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Запуск потока
        autoCorrectThread.setDaemon(true);
        autoCorrectThread.start();

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

        // Добавление обработки нажатия пробела
        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyChar() == ' ') {
                    performAutoCorrect(textArea, autoCorrectMap);
                }
            }
        });

        // Настройка макета и добавление компонентов
        frame.setJMenuBar(menuBar);
        frame.add(scrollPane);

        // Отображение окна
        frame.setVisible(true);
    }

    private void performAutoCorrect(JTextArea textArea, Map<String, String> autoCorrectMap) {
        // Получение текста
        String text = textArea.getText();
        String[] words = text.split("\\s+");
        StringBuilder correctedText = new StringBuilder();

        // Пробегаемся по словам
        for (String word : words) {
            String correctedWord = autoCorrectMap.getOrDefault(word, word);
            correctedText.append(correctedWord).append(" ");
        }

        // Обновляем текст в текстовом поле
        textArea.setText(correctedText.toString());
    }
}
