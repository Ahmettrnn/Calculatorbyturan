    import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {

    private JTextField textField;
    private JTextArea resultTextArea;
    private JLabel historyLabel;

    public Calculator() {
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(5, 3));
        for (int i = 1; i <= 9; i++) {
            JButton button = new JButton("" + i);
            button.addActionListener(this);
            p1.add(button);
        }
        JButton zeroButton = new JButton("0");
        zeroButton.addActionListener(this);
        p1.add(zeroButton);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(this);
        p1.add(calculateButton);

        JButton plusButton = new JButton("+");
        plusButton.addActionListener(this);
        p1.add(plusButton);

        JButton minusButton = new JButton("-");
        minusButton.addActionListener(this);
        p1.add(minusButton);

        JButton multiplyButton = new JButton("x");
        multiplyButton.addActionListener(this);
        p1.add(multiplyButton);

        JButton divideButton = new JButton("÷");
        divideButton.addActionListener(this);
        p1.add(divideButton);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(this);

        JPanel p2 = new JPanel(new BorderLayout());
        textField = new JTextField();
        p2.add(textField, BorderLayout.NORTH);
        p2.add(p1, BorderLayout.CENTER);
        p2.add(refreshButton, BorderLayout.SOUTH);
        add(p2, BorderLayout.EAST);

        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        add(new JScrollPane(resultTextArea), BorderLayout.CENTER);

        historyLabel = new JLabel("History: ");
        add(historyLabel, BorderLayout.WEST);

       // Image img1=new ImageIcon(Calculator.class.getResource("/1011863.png")).getImage();
        //calculateButton.setIcon(new ImageIcon(img1));

        Image img2 = new ImageIcon(Calculator.class.getResource("/1011863.png")).getImage();
        img2 = img2.getScaledInstance(400, 200, Image.SCALE_DEFAULT);
        p2.add(new JLabel(new ImageIcon(img2)), BorderLayout.PAGE_START);

        setSize(800, 400); // Genişlik artırıldı
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton clickedButton = (JButton) e.getSource();
            String buttonText = clickedButton.getText();

            if ("0123456789".contains(buttonText)) {
                textField.setText(textField.getText() + buttonText);
            } else if ("+-x÷".contains(buttonText)) {
                textField.setText(textField.getText() + " " + buttonText + " ");
            } else if (clickedButton.getText().equals("Calculate")) {
                calculateResult();
            } else if (clickedButton.getText().equals("Refresh")) {
                refresh();
            }
        }
    }

    private void calculateResult() {
        String expression = textField.getText();
        String[] elements = expression.split(" ");

        try {
            double num1 = Double.parseDouble(elements[0]);
            double num2 = Double.parseDouble(elements[2]);
            String operator = elements[1];

            double result = 0;

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "x":
                    result = num1 * num2;
                    break;
                case "÷":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        JOptionPane.showMessageDialog(this, "Cannot divide by zero.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Invalid operator.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
            }

            // JTextArea güncelleniyor
            resultTextArea.setText("Result: " + result);

            // JLabel güncelleniyor
            historyLabel.setText(historyLabel.getText() + expression + " ");

            // JTextField temizleniyor
            textField.setText("");
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(this, "Invalid expression.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refresh() {
        // JTextArea ve JLabel güncelleniyor
        resultTextArea.setText("");
        historyLabel.setText("History: ");

        // JTextField temizleniyor
        textField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculator());
    }
}
