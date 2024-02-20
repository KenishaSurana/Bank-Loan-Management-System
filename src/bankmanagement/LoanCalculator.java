package bankmanagement;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class LoanCalculator {
    private static void insertStudentLoanData(String firstName, String middleName, String lastName, int age, String document10th, String document12th) {
        // JDBC URL, username, and password of MySQL server
        String url = "jdbc:mysql://localhost:3306/bank2";
        String user = "root";
        String password = "Kittu1311";
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // The SQL query to create the student_loan table if it does not exist
            String createTableQuery = "CREATE TABLE IF NOT EXISTS student_loan ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "first_name VARCHAR(255) NOT NULL,"
                    + "middle_name VARCHAR(255),"
                    + "last_name VARCHAR(255) NOT NULL,"
                    + "age INT NOT NULL,"
                    + "document_10th VARCHAR(255) NOT NULL,"
                    + "document_12th VARCHAR(255) NOT NULL)";

            Statement statement = connection.createStatement();
            statement.executeUpdate(createTableQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // The SQL query to insert data into the student_loan table
            String query = "INSERT INTO student_loan (first_name, middle_name, last_name, age, document_10th, document_12th) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, middleName);
                preparedStatement.setString(3, lastName);
                preparedStatement.setInt(4, age);
                preparedStatement.setString(5, document10th);
                preparedStatement.setString(6, document12th);

                // Execute the query
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertCarLoanData(String name, String carBrand, String modelName,int modelYear, String licenseDocument, double balance) {
        String url = "jdbc:mysql://localhost:3306/bank2"; // Change this URL according to your MySQL configuration
        String user = "root";
        String password = "Kittu1311";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // The SQL query to create the student_loan table if it does not exist
            String createTableSQL = "CREATE TABLE IF NOT EXISTS car_loan (" +
            "id INT AUTO_INCREMENT PRIMARY KEY," +
            "name VARCHAR(255)," +
            "car_brand VARCHAR(255)," +
            "model_name VARCHAR(255)," +
            "model_year INT," +
            "license_document VARCHAR(255)," +
            "balance DOUBLE)";

            Statement statement = connection.createStatement();
            statement.executeUpdate(createTableSQL);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO car_loan (name, car_brand, model_name, model_year, license_document, balance) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, carBrand);
                preparedStatement.setString(3, modelName);
                preparedStatement.setInt(4, modelYear);
                preparedStatement.setString(5, licenseDocument);
                preparedStatement.setDouble(6, balance);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {

        // Create the main frame
        Color backgroundColor = new Color(44, 62, 80);
        Color buttonColor = new Color(5, 1, 0);
        Color textColor = new Color(0, 0, 0);

        // Create the main frame
        JFrame frame = new JFrame("Loan Calculator");
        frame.getContentPane().setBackground(backgroundColor);

        // Create the "Student Loan" button
        JButton studentLoanButton = new JButton("Student Loan");
        studentLoanButton.setBackground(buttonColor);
        studentLoanButton.setForeground(textColor);

        // Create the "Car Loan" button
        JButton carLoanButton = new JButton("Car Loan");
        carLoanButton.setBackground(buttonColor);
        carLoanButton.setForeground(textColor);

        // Add action listeners to the buttons
        studentLoanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the action for the "Student Loan" button
                showStudentLoanForm();
            }
        });

        carLoanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the action for the "Car Loan" button
                showCarLoanForm();
            }
        });

        // Create a panel to hold the buttons
        JPanel panel = new JPanel();
        panel.setBackground(backgroundColor);
        panel.add(studentLoanButton);
        panel.add(carLoanButton);

        // Add the panel to the frame
        frame.add(panel);

        // Set frame properties
        frame.setSize(300, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static void showStudentLoanForm() {
        JFrame studentLoanFrame = new JFrame("Student Loan Form");
        studentLoanFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Use the color scheme from the bank management application
        Color backgroundColor = new Color(44, 62, 80);
        Color buttonColor = new Color(5, 1, 0);
        Color textColor = new Color(0, 0, 0);

        // Create form components for Student Loan
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(10, 2));
        formPanel.setBackground(backgroundColor);

        // First Name
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setForeground(textColor);
        formPanel.add(firstNameLabel);
        JTextField firstNameField = new JTextField(20);
        firstNameField.setBackground(buttonColor);
        firstNameField.setForeground(textColor);
        formPanel.add(firstNameField);

        // Middle Name
        JLabel middleNameLabel = new JLabel("Middle Name:");
        middleNameLabel.setForeground(textColor);
        formPanel.add(middleNameLabel);
        JTextField middleNameField = new JTextField(20);
        middleNameField.setBackground(buttonColor);
        middleNameField.setForeground(textColor);
        formPanel.add(middleNameField);

        // Last Name
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setForeground(textColor);
        formPanel.add(lastNameLabel);
        JTextField lastNameField = new JTextField(20);
        lastNameField.setBackground(buttonColor);
        lastNameField.setForeground(textColor);
        formPanel.add(lastNameField);

        JLabel AgeLabel = new JLabel("Age:");
        AgeLabel.setForeground(textColor);
        formPanel.add(AgeLabel);
        JTextField ageField = new JTextField(3);
        ageField.setBackground(buttonColor);
        ageField.setForeground(textColor);
        formPanel.add(ageField);

        JLabel AmtLabel = new JLabel("Amount:");
        AmtLabel.setForeground(textColor);
        formPanel.add(AmtLabel);
        JTextField amountField = new JTextField(10);
        amountField.setBackground(buttonColor);
        amountField.setForeground(textColor);
        formPanel.add(amountField);

        // 10th Mark Sheet Document
        JButton document10thButton = new JButton("Select 10th Mark Sheet Document");
        JTextField document10thField = new JTextField(20);
        document10thField.setEditable(false);
        document10thField.setBackground(buttonColor);
        document10thField.setForeground(textColor);

        document10thButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    String selectedFile = fileChooser.getSelectedFile().getName();
                    document10thField.setText(selectedFile);
                }
            }
        });

        JLabel TenthLabel = new JLabel("10th Mark Sheet Document:");
        TenthLabel.setForeground(textColor);
        formPanel.add(TenthLabel);
        formPanel.add(document10thButton);

        // 12th Mark Sheet Document
        JButton document12thButton = new JButton("Select 12th Mark Sheet Document");
        JTextField document12thField = new JTextField(20);
        document12thField.setEditable(false);
        document12thField.setBackground(buttonColor);
        document12thField.setForeground(textColor);

        document12thButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    String selectedFile = fileChooser.getSelectedFile().getName();
                    document12thField.setText(selectedFile);
                }
            }
        });

        JLabel TwelthLabel = new JLabel("12th Mark Sheet Document:");
        TwelthLabel.setForeground(textColor);
        formPanel.add(TwelthLabel);
        formPanel.add(document12thButton);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String middleName = middleNameField.getText();
                String lastName = lastNameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String document10th = document10thField.getText();
                String document12th = document12thField.getText();

                if (firstName.isEmpty() || middleName.isEmpty() || lastName.isEmpty() || age < 18 || document10th.isEmpty() || document12th.isEmpty()) {
                    JOptionPane.showMessageDialog(studentLoanFrame, "Not eligible for loan");
                } else {
                    // Insert data into MySQL database
                    insertStudentLoanData(firstName, middleName, lastName, age, document10th, document12th);

                    JOptionPane.showMessageDialog(studentLoanFrame, "Your loan request is submitted");
                }
                studentLoanFrame.dispose();
            }
        });
        submitButton.setBackground(buttonColor);
        submitButton.setForeground(textColor);
        formPanel.add(submitButton);

        studentLoanFrame.add(formPanel);

        // Set frame properties for the Student Loan form
        studentLoanFrame.setSize(400, 350);
        studentLoanFrame.setVisible(true);
    }

    private static void showCarLoanForm() {
        JFrame carLoanFrame = new JFrame("Car Loan Form");
        carLoanFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
        Color backgroundColor = new Color(44, 62, 80);
        Color buttonColor = new Color(5, 1, 0);
        Color textColor = new Color(0, 0, 0);
    
        JPanel formPanel = new JPanel(null); // Use null layout for manual positioning
        formPanel.setBackground(backgroundColor);
    
        // Name
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setForeground(textColor);
        nameLabel.setBounds(10, 10, 80, 25);
        formPanel.add(nameLabel);
        JTextField nameField = new JTextField(20);
        nameField.setBackground(buttonColor);
        nameField.setForeground(textColor);
        nameField.setBounds(186, 10, 200, 25);
        formPanel.add(nameField);
    
        // Car Brand Name
        JLabel carBrandLabel = new JLabel("Car Brand Name:");
        carBrandLabel.setForeground(textColor);
        carBrandLabel.setBounds(10, 40, 100, 25);
        formPanel.add(carBrandLabel);
        JTextField carBrandField = new JTextField(20);
        carBrandField.setBackground(buttonColor);
        carBrandField.setForeground(textColor);
        carBrandField.setBounds(186, 40, 200, 25);
        formPanel.add(carBrandField);
    
        // Model Name
        JLabel modelNameLabel = new JLabel("Model Name:");
        modelNameLabel.setForeground(textColor);
        modelNameLabel.setBounds(10, 70, 80, 25);
        formPanel.add(modelNameLabel);
        JTextField modelNameField = new JTextField(20);
        modelNameField.setBackground(buttonColor);
        modelNameField.setForeground(textColor);
        modelNameField.setBounds(186, 70, 200, 25);
        formPanel.add(modelNameField);
    
        // Model Year
        JLabel modelYearLabel = new JLabel("Model Year:");
        modelYearLabel.setForeground(textColor);
        modelYearLabel.setBounds(10, 100, 80, 25);
        formPanel.add(modelYearLabel);
        JTextField modelYearField = new JTextField(4);
        modelYearField.setBackground(buttonColor);
        modelYearField.setForeground(textColor);
        modelYearField.setBounds(186, 100, 200, 25);
        formPanel.add(modelYearField);
    
        // Document selection
        JButton documentButton = new JButton("Select License Document");
        documentButton.setBounds(186,130, 200, 25); //10.130
        JLabel documentLabel = new JLabel("License Document:");
        documentLabel.setForeground(textColor);
        documentLabel.setBounds(10,130, 150, 25); //220.130
        formPanel.add(documentLabel);
        JTextField documentField = new JTextField(20);
        documentField.setEditable(false);
        documentField.setBackground(buttonColor);
        documentField.setForeground(textColor);
        documentField.setBounds(450, 130, 150, 25);
        formPanel.add(documentField);
    
        documentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
    
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    String selectedFile = fileChooser.getSelectedFile().getName();
                    documentField.setText(selectedFile);
                }
            }
        });
    
        formPanel.add(documentButton);
    
        // Current Account Balance
        JLabel balanceLabel = new JLabel("Current Account Balance:");
        balanceLabel.setForeground(textColor);
        balanceLabel.setBounds(10, 160, 150, 25);
        formPanel.add(balanceLabel);
        JTextField balanceField = new JTextField(10);
        balanceField.setBackground(buttonColor);
        balanceField.setForeground(textColor);
        balanceField.setBounds(186, 160, 200, 25);
        formPanel.add(balanceField);
    
        // Submit Button
        JButton submitButton = new JButton("Submit");
        submitButton.setBackground(buttonColor);
        submitButton.setForeground(textColor);
        submitButton.setBounds(150, 200, 100, 30);
    
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get values from the form
                String name = nameField.getText();
                String carBrand = carBrandField.getText();
                String modelName = modelNameField.getText();
                int modelYear = Integer.parseInt(modelYearField.getText());
                String licenseDocument = documentField.getText();
                double balance = Double.parseDouble(balanceField.getText());
    
                // Insert data into MySQL database
                insertCarLoanData(name, carBrand, modelName, modelYear, licenseDocument, balance);
    
                // Display appropriate message
                int age = Integer.parseInt(modelYearField.getText());
                if (age >= 24) {
                    JOptionPane.showMessageDialog(carLoanFrame, "Your loan request is submitted");
                } else {
                    JOptionPane.showMessageDialog(carLoanFrame, "Not eligible for loan");
                }
                carLoanFrame.dispose();
            }
        });
    
        formPanel.add(submitButton);
    
        carLoanFrame.add(formPanel);
    
        // Set frame properties for the Car Loan form
        carLoanFrame.setSize(400, 300);
        carLoanFrame.setVisible(true);
    }
    
}
