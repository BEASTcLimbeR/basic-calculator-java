import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * iPhone-Style Calculator with GUI
 * This calculator performs basic math operations with an iPhone-inspired design
 * 
 * Features:
 * - iPhone-style dark theme interface
 * - Rounded buttons with proper colors
 * - Number buttons (0-9) in dark gray
 * - Operation buttons (+, -, *, /) in orange
 * - Clear and equals buttons with proper styling
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
    
    // iPhone-style colors
    private static final Color DARK_BACKGROUND = new Color(0, 0, 0);
    private static final Color DISPLAY_BACKGROUND = new Color(0, 0, 0);
    private static final Color DISPLAY_TEXT = new Color(255, 255, 255);
    private static final Color GRAY_BUTTON = new Color(160, 160, 160);
    private static final Color DARK_GRAY_BUTTON = new Color(51, 51, 51);
    private static final Color ORANGE_BUTTON = new Color(255, 149, 0);
    private static final Color ORANGE_BUTTON_PRESSED = new Color(255, 165, 0);
    
    // Calculator logic variables
    private double firstNumber = 0;       // Stores the first number in an operation
    private String operation = "";        // Stores the current operation (+, -, *, /)
    private boolean operationPressed = false; // Tracks if an operation button was pressed
    
    /**
     * Constructor - sets up the iPhone-style calculator GUI
     */
    public Calculator() {
        // Set up the main window with iPhone-style dimensions
        setTitle("iPhone Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 500); // iPhone-like proportions
        setLocationRelativeTo(null); // Center the window on screen
        setResizable(false);
        
        // Set dark background
        getContentPane().setBackground(DARK_BACKGROUND);
        
        // Create and set up the GUI components
        setupComponents();
        layoutComponents();
        
        // Make the window visible
        setVisible(true);
    }
    
    /**
     * Creates and initializes all GUI components with iPhone-style design
     */
    private void setupComponents() {
        // Create the display field with iPhone-style appearance
        display = new JTextField();
        display.setFont(new Font("SF Pro Display", Font.PLAIN, 48)); // iPhone-like font size
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false); // Users can't type directly into the display
        display.setText("0");
        display.setBackground(DISPLAY_BACKGROUND);
        display.setForeground(DISPLAY_TEXT);
        display.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Create number buttons (0-9) with iPhone-style colors
        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(new Font("SF Pro Display", Font.PLAIN, 32));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setBackground(DARK_GRAY_BUTTON);
            numberButtons[i].setForeground(Color.WHITE);
            numberButtons[i].setFocusPainted(false);
            numberButtons[i].setBorderPainted(false);
            numberButtons[i].setOpaque(true);
        }
        
        // Create operation buttons with iPhone orange color
        operationButtons = new JButton[4];
        String[] operations = {"+", "−", "×", "÷"}; // iPhone-style symbols
        for (int i = 0; i < 4; i++) {
            operationButtons[i] = new JButton(operations[i]);
            operationButtons[i].setFont(new Font("SF Pro Display", Font.PLAIN, 32));
            operationButtons[i].addActionListener(this);
            operationButtons[i].setBackground(ORANGE_BUTTON);
            operationButtons[i].setForeground(Color.WHITE);
            operationButtons[i].setFocusPainted(false);
            operationButtons[i].setBorderPainted(false);
            operationButtons[i].setOpaque(true);
        }
        
        // Create clear button (gray for iPhone style)
        clearButton = new JButton("C");
        clearButton.setFont(new Font("SF Pro Display", Font.PLAIN, 32));
        clearButton.addActionListener(this);
        clearButton.setBackground(GRAY_BUTTON);
        clearButton.setForeground(Color.BLACK);
        clearButton.setFocusPainted(false);
        clearButton.setBorderPainted(false);
        clearButton.setOpaque(true);
        
        // Create equals button (orange for iPhone style)
        equalsButton = new JButton("=");
        equalsButton.setFont(new Font("SF Pro Display", Font.PLAIN, 32));
        equalsButton.addActionListener(this);
        equalsButton.setBackground(ORANGE_BUTTON);
        equalsButton.setForeground(Color.WHITE);
        equalsButton.setFocusPainted(false);
        equalsButton.setBorderPainted(false);
        equalsButton.setOpaque(true);
        
        // Create decimal button (dark gray for iPhone style)
        decimalButton = new JButton(".");
        decimalButton.setFont(new Font("SF Pro Display", Font.PLAIN, 32));
        decimalButton.addActionListener(this);
        decimalButton.setBackground(DARK_GRAY_BUTTON);
        decimalButton.setForeground(Color.WHITE);
        decimalButton.setFocusPainted(false);
        decimalButton.setBorderPainted(false);
        decimalButton.setOpaque(true);
    }
    
    /**
     * Arranges all components in iPhone calculator layout
     */
    private void layoutComponents() {
        setLayout(new BorderLayout());
        
        // Add display at the top
        add(display, BorderLayout.NORTH);
        
        // Create button panel with iPhone-style grid layout
        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 8, 8));
        buttonPanel.setBackground(DARK_BACKGROUND);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // iPhone calculator layout (5 rows, 4 columns)
        // Row 1: C, ±, %, ÷
        buttonPanel.add(clearButton);
        buttonPanel.add(createGrayButton("±")); // Plus/minus (placeholder)
        buttonPanel.add(createGrayButton("%"));  // Percentage (placeholder)
        buttonPanel.add(operationButtons[3]); // ÷
        
        // Row 2: 7, 8, 9, ×
        buttonPanel.add(numberButtons[7]);
        buttonPanel.add(numberButtons[8]);
        buttonPanel.add(numberButtons[9]);
        buttonPanel.add(operationButtons[2]); // ×
        
        // Row 3: 4, 5, 6, −
        buttonPanel.add(numberButtons[4]);
        buttonPanel.add(numberButtons[5]);
        buttonPanel.add(numberButtons[6]);
        buttonPanel.add(operationButtons[1]); // −
        
        // Row 4: 1, 2, 3, +
        buttonPanel.add(numberButtons[1]);
        buttonPanel.add(numberButtons[2]);
        buttonPanel.add(numberButtons[3]);
        buttonPanel.add(operationButtons[0]); // +
        
        // Row 5: 0 (spans 2 columns), ., =
        JPanel bottomRow = new JPanel(new GridLayout(1, 4, 8, 8));
        bottomRow.setBackground(DARK_BACKGROUND);
        
        // Create wide 0 button
        JButton zeroButton = new JButton("0");
        zeroButton.setFont(new Font("SF Pro Display", Font.PLAIN, 32));
        zeroButton.addActionListener(this);
        zeroButton.setBackground(DARK_GRAY_BUTTON);
        zeroButton.setForeground(Color.WHITE);
        zeroButton.setFocusPainted(false);
        zeroButton.setBorderPainted(false);
        zeroButton.setOpaque(true);
        
        // Add 0 button spanning 2 columns
        JPanel zeroPanel = new JPanel(new BorderLayout());
        zeroPanel.setBackground(DARK_BACKGROUND);
        zeroPanel.add(zeroButton, BorderLayout.CENTER);
        
        bottomRow.add(zeroPanel);
        bottomRow.add(new JLabel("")); // Empty space
        bottomRow.add(decimalButton);
        bottomRow.add(equalsButton);
        
        buttonPanel.add(bottomRow);
        
        add(buttonPanel, BorderLayout.CENTER);
    }
    
    /**
     * Creates a gray button for iPhone-style interface
     */
    private JButton createGrayButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("SF Pro Display", Font.PLAIN, 32));
        button.setBackground(GRAY_BUTTON);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setEnabled(false); // Disable for now (placeholders)
        return button;
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
        // Handle operation buttons (+, −, ×, ÷)
        else if (command.matches("[+−×÷]")) {
            // Convert iPhone symbols to standard symbols for calculation
            String operation = convertSymbol(command);
            handleOperationInput(operation);
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
     * Converts iPhone-style symbols to standard operation symbols
     */
    private String convertSymbol(String symbol) {
        switch (symbol) {
            case "−": return "-";
            case "×": return "*";
            case "÷": return "/";
            default: return symbol;
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
