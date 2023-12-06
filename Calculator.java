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
            button.setBackground(Color.YELLOW);
            button.setForeground(Color.black);
            button.setFont(new Font("Modern",Font.BOLD,15));
            p1.add(button);
        }
        JButton zeroButton = new JButton("0");
        zeroButton.addActionListener(this);
        zeroButton.setFont(new Font("Modern",Font.BOLD,15));
        zeroButton.setBackground(Color.yellow);
        p1.add(zeroButton);
        //Buttonun rengi ve fontu değiştirildi.
        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(this);
        calculateButton.setBackground(new Color(0, 206, 180));
        calculateButton.setFont(new Font("Modern",Font.BOLD,15));
        calculateButton.setForeground(Color.WHITE);
        p1.add(calculateButton);

        JButton plusButton = new JButton("+");
        plusButton.addActionListener(this);
        plusButton.setFont(new Font("Modern",Font.BOLD,15));
        plusButton.setBackground(Color.yellow);
        p1.add(plusButton);

        JButton minusButton = new JButton("-");
        minusButton.addActionListener(this);
        minusButton.setFont(new Font("Modern",Font.BOLD,15));
        minusButton.setBackground(Color.yellow);
        p1.add(minusButton);

        JButton multiplyButton = new JButton("x");
        multiplyButton.addActionListener(this);
        multiplyButton.setFont(new Font("Modern",Font.BOLD,15));
        multiplyButton.setBackground(Color.yellow);
        p1.add(multiplyButton);

        JButton divideButton = new JButton("÷");
        divideButton.addActionListener(this);
        divideButton.setFont(new Font("Modern",Font.BOLD,15));
        divideButton.setBackground(Color.yellow);
        p1.add(divideButton);
        //Buttonun rengi ve fontu değiştirildi.
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(this);
        refreshButton.setBackground(Color.red);
        refreshButton.setFont(new Font("Modern",Font.BOLD,20));
        refreshButton.setForeground(Color.WHITE);
        
        //Panelin rengi değiştirildi.
        JPanel p2 = new JPanel(new BorderLayout());
        textField = new JTextField();
        textField.setBackground(new Color(0, 206, 180));
        textField.setForeground(Color.blue);
        textField.setPreferredSize(new Dimension(500,20)); // Genişlik ve yükseklik ayarları

        p2.setBackground(new Color(0, 206, 180));
        p2.add(p1, BorderLayout.CENTER);
        p2.add(refreshButton, BorderLayout.SOUTH);
        p2.add(textField,BorderLayout.NORTH);     //Yazarken yazdığın işlemleri görmek için kullanılan textfieldı sol alta koymak istiyorum fakat olmuyor.
        add(p2, BorderLayout.EAST);
       
        
        

        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        add(new JScrollPane(resultTextArea), BorderLayout.CENTER);
        resultTextArea.setBackground(new Color(0, 206, 180));
        
        //Labelin rengi değiştirildi.
        historyLabel = new JLabel("History: ");
        historyLabel.setForeground(Color.blue);
        add(historyLabel, BorderLayout.WEST);
        setVisible(true);

       
        Image jicon=new ImageIcon(Calculator.class.getResource("/1011863.png")).getImage();
        setIconImage(jicon);
        //Uygulamanın sol üst köşesindeki iconu değiştirdim.

        Image img2 = new ImageIcon(Calculator.class.getResource("/1011863.png")).getImage();
        img2 = img2.getScaledInstance(400, 200, Image.SCALE_DEFAULT);
        p2.add(new JLabel(new ImageIcon(img2)), BorderLayout.PAGE_START);

        setSize(800, 400); // Genişlik artırıldı
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
    
    //Buttonlara görevlerini verdim.
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
            resultTextArea.setForeground(Color.blue);
            resultTextArea.setFont(new Font("Modern",Font.BOLD,20));
            


            // JLabel güncelleniyor
            historyLabel.setText(historyLabel.getText() + "\n"+ expression +" " );
            historyLabel.setForeground(Color.blue);

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
        SwingUtilities.invokeLater(() -> new Calculator());  //Swing bileşenlerinin güvenli bir şekilde başlatılmasını sağlar(Lambda expressionsu kullanarak).
    
    };
}

