# Simple Calculator

A beginner-friendly calculator application built with Java Swing that performs basic mathematical operations.

## Features

- **Clean GUI Interface**: Modern, easy-to-use graphical interface
- **Basic Operations**: Addition (+), Subtraction (-), Multiplication (*), Division (/)
- **Number Input**: Full number pad (0-9) with decimal point support
- **Error Handling**: Prevents division by zero and handles invalid inputs
- **Clear Function**: Reset the calculator to start fresh

## Prerequisites

Before running this calculator, you need to have Java installed on your computer:

### Installing Java (if not already installed)

1. **Download Java Development Kit (JDK)**:
   - Go to [Oracle's Java website](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/)
   - Download the latest JDK for Windows
   - Run the installer and follow the setup instructions

2. **Verify Installation**:
   - Open Command Prompt or PowerShell
   - Type `java -version` and press Enter
   - You should see version information if Java is installed correctly

3. **Set PATH (if needed)**:
   - If `java` or `javac` commands are not recognized, you may need to add Java to your system PATH
   - The Java installation usually does this automatically, but you can manually add it if needed

## How to Run the Calculator

1. **Compile the Java file**:
   ```bash
   javac Calculator.java
   ```

2. **Run the calculator**:
   ```bash
   java Calculator
   ```

## How to Use the Calculator

1. **Enter Numbers**: Click the number buttons (0-9) to input numbers
2. **Choose Operation**: Click one of the operation buttons (+, -, *, /)
3. **Enter Second Number**: Click number buttons for the second number
4. **Calculate**: Click the "=" button to see the result
5. **Clear**: Click "C" to clear the display and start over
6. **Decimal Numbers**: Click "." to add decimal points to your numbers

## Example Calculations

- **Addition**: 5 + 3 = 8
- **Subtraction**: 10 - 4 = 6
- **Multiplication**: 6 * 7 = 42
- **Division**: 15 / 3 = 5

## Code Structure

The calculator is built using Java Swing components:

- **JFrame**: Main window container
- **JTextField**: Display area for numbers and results
- **JButton**: Interactive buttons for numbers and operations
- **ActionListener**: Handles button click events
- **GridLayout**: Organizes buttons in a calculator-like layout

## Learning Points

This project demonstrates several important Java concepts:

1. **GUI Programming**: Using Swing components to create user interfaces
2. **Event Handling**: Responding to user interactions (button clicks)
3. **Layout Management**: Organizing components in a window
4. **Exception Handling**: Managing errors like division by zero
5. **Object-Oriented Programming**: Creating classes and methods

## Troubleshooting

- **"javac is not recognized"**: Java JDK is not installed or not in PATH
- **"java is not recognized"**: Java Runtime Environment is not installed or not in PATH
- **Calculator doesn't open**: Make sure you're running `java Calculator` (not `java Calculator.java`)

## Next Steps

Once you're comfortable with this basic calculator, you could enhance it by adding:

- Memory functions (M+, M-, MR, MC)
- More operations (square root, percentage, etc.)
- Keyboard input support
- History of calculations
- Different themes or colors
