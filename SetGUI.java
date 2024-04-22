import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public class SetGUI extends JFrame {
    private Set<Integer> set;
    private JTextArea textArea;

    public SetGUI() {
        set = new HashSet<>();
        textArea = new JTextArea(10, 20);

        JTextField inputField = new JTextField(10);

        JButton addButton = new JButton("Добавить элемент");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int value = Integer.parseInt(inputField.getText());
                    set.add(value);
                    updateSet();
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Пожалуйста, введите целое число.");
                }
            }
        });

        JButton removeButton = new JButton("Удалить элемент");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int value = Integer.parseInt(inputField.getText());
                    set.remove(value);
                    updateSet();
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Пожалуйста, введите целое число.");
                }
            }
        });

        JPanel panel = new JPanel();
        panel.add(inputField);
        panel.add(addButton);
        panel.add(removeButton);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        setTitle("Множество");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateSet() {
        textArea.setText("");
        for (int element : set) {
            textArea.append(element + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SetGUI::new);
    }
}