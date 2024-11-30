import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.Font;

public class Calculator extends JFrame implements ActionListener {
    private JTextField textField;
    private JButton[] numberButtons = new JButton[10];
    private JButton[] operationButtons = new JButton[9];
    private JPanel panel;
    private double total = 0;
    private char action;

    public Calculator() {
        super("Калькулятор");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        Font font = new Font("Arial", Font.BOLD, 18);

        textField = new JTextField(16);
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setFont(font);
        textField.setEditable(false);

        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));

        for (int i = 1; i < 10; i++) {
            numberButtons[i] = new JButton(Integer.toString(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(font);
            panel.add(numberButtons[i]);
        }

        JButton buttonPoint = new JButton(".");
        buttonPoint.addActionListener(this);
        buttonPoint.setFont(font);
        panel.add(buttonPoint);

        JButton buttonClear = new JButton("C");
        buttonClear.addActionListener(this);
        buttonClear.setFont(font);
        panel.add(buttonClear);

        JButton buttonZero = new JButton("0");
        buttonZero.addActionListener(this);
        buttonZero.setFont(font);
        panel.add(buttonZero);

        JButton buttonEqual = new JButton("=");
        buttonEqual.addActionListener(this);
        buttonEqual.setFont(font);
        panel.add(buttonEqual);

        JButton buttonPlus = new JButton("+");
        buttonPlus.addActionListener(this);
        buttonPlus.setFont(font);
        panel.add(buttonPlus);

        JButton buttonMinus = new JButton("-");
        buttonMinus.addActionListener(this);
        buttonMinus.setFont(font);
        panel.add(buttonMinus);

        JButton buttonMultiply = new JButton("*");
        buttonMultiply.addActionListener(this);
        buttonMultiply.setFont(font);
        panel.add(buttonMultiply);

        JButton buttonDivide = new JButton("/");
        buttonDivide.addActionListener(this);
        buttonDivide.setFont(font);
        panel.add(buttonDivide);

        setLayout(null);

        add(textField);
        textField.setBounds(10, 30, 250, 40);

        add(panel);
        panel.setBounds(10, 70, 240, 220);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("=")) {
            arithmeticOperation(command);
        } else {
            String currentValue = textField.getText();
            textField.setText(currentValue + command);
        }
    }

    public void arithmeticOperation(char operator) {
        Double oldTotal = Double.parseDouble(textField.getText());
        switch (operator) {
            case '+':
                total += oldTotal;
                break;
            case '-':
                total -= oldTotal;
                break;
            case '*':
                total *= oldTotal;
                break;
            case '/':
                total /= oldTotal;
                break;
        }
        textField.setText(Double.toString(total));
        total = 0;
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
