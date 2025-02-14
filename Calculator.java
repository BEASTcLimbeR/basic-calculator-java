import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Simple Calculator with GUI
 * This calculator performs basic math operations: addition, subtraction, multiplication, and division
 * 
 * Features:
 * - Clean, modern interface
 * - Number buttons (0-9)
 * - Operation buttons (+, -, *, /)
 * - Clear and equals buttons
 * - Error handling for division by zero
 */
public class Calculator extends JFrame implements ActionListener {
    
    // GUI Components
    private JTextField display;           // The text field that shows numbers and results
    private JButton[] numberButtons;      // Array to hold number buttons (0-9)
    private JButton[] operationButtons;   // Array to hold operation buttons (+, -, *, /)
    private JButton clearButton;          // Button to clear the display
    private JButton equalsButton;         // Button to calculate the result
    private JButton decimalButton;        // Button for decimal point
    
    // Calculator logic variables
    private double firstNumber = 0;       // Stores the first number in an operation
    private String operation = "";        // Stores the current operation (+, -, *, /)
    private boolean operationPressed = false; // Tracks if an operation button was pressed
    
    /**
     * Constructor - sets up the calculator GUI
     */
    public Calculator() {
        // Set up the main window
        setTitle("Simple Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLocationRelativeTo(null); // Center the window on screen
        setResizable(false);
        
        // Create and set up the GUI components
        setupComponents();
        layoutComponents();
        
        // Make the window visible
        setVisible(true);
    }
    
    /**
     * Creates and initializes all GUI components
     */
    private void setupComponents() {
        // Create the display field
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 20));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false); // Users can't type directly into the display
        display.setText("0");
        
        // Create number buttons (0-9)
        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(new Font("Arial", Font.BOLD, 16));
            numberButtons[i].addActionListener(this);
        }
        
        // Create operation buttons
        operationButtons = new JButton[4];
        String[] operations = {"+", "-", "*", "/"};
        for (int i = 0; i < 4; i++) {
            operationButtons[i] = new JButton(operations[i]);
            operationButtons[i].setFont(new Font("Arial", Font.BOLD, 16));
            operationButtons[i].addActionListener(this);
            operationButtons[i].setBackground(new Color(255, 165, 0)); // Orange color for operations
        }
        
        // Create other buttons
        clearButton = new JButton("C");
        clearButton.setFont(new Font("Arial", Font.BOLD, 16));
        clearButton.addActionListener(this);
        clearButton.setBackground(new Color(255, 99, 71)); // Red color for clear
        
        equalsButton = new JButton("=");
        equalsButton.setFont(new Font("Arial", Font.BOLD, 16));
        equalsButton.addActionListener(this);
        equalsButton.setBackground(new Color(50, 205, 50)); // Green color for equals
        
        decimalButton = new JButton(".");
        decimalButton.setFont(new Font("Arial", Font.BOLD, 16));
        decimalButton.addActionListener(this);
    }
    
    /**
     * Arranges all components in a grid layout
     */
    private void layoutComponents() {
        setLayout(new BorderLayout());
        
        // Add display at the top
        add(display, BorderLayout.NORTH);
        
        // Create button panel with grid layout
        JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 5, 5));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Add buttons in calculator layout
        // Row 1: Clear, /, *, -
        buttonPanel.add(clearButton);
        buttonPanel.add(operationButtons[3]); // /
        buttonPanel.add(operationButtons[2]); // *
        buttonPanel.add(operationButtons[1]); // -
        
        // Row 2: 7, 8, 9, +
        buttonPanel.add(numberButtons[7]);
        buttonPanel.add(numberButtons[8]);
        buttonPanel.add(numberButtons[9]);
        buttonPanel.add(operationButtons[0]); // +
        
        // Row 3: 4, 5, 6, =
        buttonPanel.add(numberButtons[4]);
        buttonPanel.add(numberButtons[5]);
        buttonPanel.add(numberButtons[6]);
        buttonPanel.add(equalsButton);
        
        // Row 4: 1, 2, 3, 0
        buttonPanel.add(numberButtons[1]);
        buttonPanel.add(numberButtons[2]);
        buttonPanel.add(numberButtons[3]);
        buttonPanel.add(numberButtons[0]);
        
        add(buttonPanel, BorderLayout.CENTER);
    }
    
    /**
     * Handles button click events
     * This method is called whenever any button is clicked
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        // Handle number buttons (0-9)
        if (command.matches("[0-9]")) {
            handleNumberInput(command);
        }
        // Handle operation buttons (+, -, *, /)
        else if (command.matches("[+\\-*/]")) {
            handleOperationInput(command);
        }
        // Handle equals button
        else if (command.equals("=")) {
            handleEquals();
        }
        // Handle clear button
        else if (command.equals("C")) {
            handleClear();
        }
        // Handle decimal button
        else if (command.equals(".")) {
            handleDecimal();
        }
    }
    
    /**
     * Handles input when a number button is pressed
     */
    private void handleNumberInput(String number) {
        if (operationPressed) {
            // If an operation was just pressed, start a new number
            display.setText(number);
            operationPressed = false;
        } else {
            // Append to current number
            String currentText = display.getText();
            if (currentText.equals("0")) {
                display.setText(number);
            } else {
                display.setText(currentText + number);
            }
        }
    }
    
    /**
     * Handles input when an operation button is pressed
     */
    private void handleOperationInput(String op) {
        if (!operationPressed) {
            // If there's already a number and operation, calculate the result first
            if (!operation.isEmpty()) {
                handleEquals();
            }
            
            // Store the current number and operation
            firstNumber = Double.parseDouble(display.getText());
            operation = op;
            operationPressed = true;
        }
    }
    
    /**
     * Handles the equals button press
     */
    private void handleEquals() {
        if (!operation.isEmpty() && !operationPressed) {
            try {
                double secondNumber = Double.parseDouble(display.getText());
                double result = calculate(firstNumber, secondNumber, operation);
                
                // Display the result
                if (result == (long) result) {
                    // If it's a whole number, display without decimal
                    display.setText(String.valueOf((long) result));
                } else {
                    // Display with decimal places
                    display.setText(String.valueOf(result));
                }
                
                // Reset for next calculation
                operation = "";
                operationPressed = true;
            } catch (NumberFormatException ex) {
                display.setText("Error");
            }
        }
    }
    
    /**
     * Performs the actual calculation
     */
    private double calculate(double first, double second, String op) {
        switch (op) {
            case "+":
                return first + second;
            case "-":
                return first - second;
            case "*":
                return first * second;
            case "/":
                if (second == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return first / second;
            default:
                return second;
        }
    }
    
    /**
     * Handles the clear button press
     */
    private void handleClear() {
        display.setText("0");
        firstNumber = 0;
        operation = "";
        operationPressed = false;
    }
    
    /**
     * Handles the decimal button press
     */
    private void handleDecimal() {
        String currentText = display.getText();
        if (operationPressed) {
            display.setText("0.");
            operationPressed = false;
        } else if (!currentText.contains(".")) {
            display.setText(currentText + ".");
        }
    }
    
    /**
     * Main method - creates and runs the calculator
     */
    public static void main(String[] args) {
        // Use SwingUtilities.invokeLater to ensure thread safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Calculator();
            }
        });
    }
}
