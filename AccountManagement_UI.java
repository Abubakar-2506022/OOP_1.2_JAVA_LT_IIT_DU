package lab_exam1;

import javax.swing.*;
import java.awt.*;

// Save this file as AccountManagement_UI.java inside the lab_exam1 package
public class AccountManagement_UI {

    public static void main(String[] args) {
        // 1. UI Input Boxes for Account 1 Creation
        String id = JOptionPane.showInputDialog("Enter Account ID:");
        String name = JOptionPane.showInputDialog("Enter Account Holder Name:");
        String gender = JOptionPane.showInputDialog("Enter Gender (male/female):");
        int initialBalance = Integer.parseInt(JOptionPane.showInputDialog("Enter Initial Balance (TK):"));

        BankAccount account1 = new BankAccount(id, name, gender, initialBalance);
        account1.AccountOpen(id, name, gender, initialBalance);

        // Second account for transferring funds (from your AccountManagement class)
        BankAccount account2 = new BankAccount("024351938", "AbuBakar", "male", 5000);

        // 2. UI Input Box for Deposit
        int depositAmt = Integer.parseInt(JOptionPane.showInputDialog("Enter amount to deposit (TK):"));
        account1.deposit(depositAmt);

        // 3. UI Input Box for Withdrawal
        int withdrawAmt = Integer.parseInt(JOptionPane.showInputDialog("Enter amount to withdraw (TK):"));
        account1.withdraw(withdrawAmt);

        // 4. UI Input Box for Transfer
        String reply = JOptionPane.showInputDialog("Do you want to transfer funds to account 2? (Y/N):");
        if (reply != null && reply.equalsIgnoreCase("Y")) {
            int transferAmt = Integer.parseInt(JOptionPane.showInputDialog("Enter amount to transfer (TK):"));
            if (transferAmt > 0 && transferAmt < account1.transactionLimit && transferAmt <= account1.currentBalance) {
                account1.withdraw(transferAmt);
                account2.deposit(transferAmt);
                JOptionPane.showMessageDialog(null, "Transferred TK " + transferAmt + " to account 2");
            } else {
                JOptionPane.showMessageDialog(null, "Transfer failed! Check balance or transaction limit.");
            }
        }

        // 5. UI Output Box
        account1.viewAccountSummary();
        String summary = "Account ID: " + id
                + "\nHolder Name: " + name
                + "\nGender: " + gender
                + "\nCurrent Balance: TK " + account1.currentBalance
                + "\nTransaction Limit: TK " + account1.transactionLimit;

        JOptionPane.showMessageDialog(null, summary, "Account Summary", JOptionPane.INFORMATION_MESSAGE);
    }

    public static class AccountManagementSwing {
        // Object references accessible by button actions
        private static BankAccount account1;
        private static BankAccount account2 = new BankAccount("024351938", "AbuBakar", "male", 5000);
        private static String savedID = "";
        private static String savedName = "";
        private static String savedGender = "";

        public static void main(String[] args) {
            // 1. Create the main window (JFrame)
            JFrame frame = new JFrame("Bank Account Manager");
            frame.setSize(420, 480);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new GridLayout(13, 1, 5, 5)); // Simple vertical layout

            // 2. UI Input Components
            JTextField idField = new JTextField();
            JTextField nameField = new JTextField();
            JTextField genderField = new JTextField();
            JTextField initBalanceField = new JTextField();
            JButton createBtn = new JButton("1. Open Account");

            JTextField amountField = new JTextField();
            JButton depositBtn = new JButton("Deposit");
            JButton withdrawBtn = new JButton("Withdraw");
            JButton transferBtn = new JButton("Transfer to Account 2");

            // 3. UI Output Labels
            JLabel statusLabel = new JLabel("Status: Enter details to open an account.");
            JLabel detailsLabel = new JLabel("Balance: TK 0 | Limit: TK 0");

            // 4. Add components to window
            frame.add(new JLabel("Account ID:"));
            frame.add(idField);

            frame.add(new JLabel("Account Holder Name:"));
            frame.add(nameField);

            frame.add(new JLabel("Gender (male / female):"));
            frame.add(genderField);

            frame.add(new JLabel("Initial Balance (TK):"));
            frame.add(initBalanceField);

            frame.add(createBtn);

            frame.add(new JLabel("Transaction Amount (TK):"));
            frame.add(amountField);

            // Put Deposit, Withdraw, and Transfer buttons side-by-side
            JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 6, 0));
            buttonPanel.add(depositBtn);
            buttonPanel.add(withdrawBtn);
            buttonPanel.add(transferBtn);
            frame.add(buttonPanel);

            // Output panel
            JPanel outputPanel = new JPanel(new GridLayout(2, 1));
            outputPanel.add(statusLabel);
            outputPanel.add(detailsLabel);
            frame.add(outputPanel);

            // 5. Button Click Actions (Events)
            createBtn.addActionListener(e -> {
                String id = idField.getText().trim();
                String name = nameField.getText().trim();
                String gender = genderField.getText().trim();
                int startBal = Integer.parseInt(initBalanceField.getText().trim());

                if (!id.isEmpty() && !name.isEmpty() && !gender.isEmpty()) {
                    account1 = new BankAccount(id, name, gender, startBal);
                    account1.AccountOpen(id, name, gender, startBal);

                    savedID = id;
                    savedName = name;
                    savedGender = gender;

                    statusLabel.setText("Status: Account Opened for " + name + "!");
                    detailsLabel.setText("Balance: TK " + account1.currentBalance + " | Limit: TK " + account1.transactionLimit);
                } else {
                    statusLabel.setText("Error: Please fill in all account fields.");
                }
            });

            depositBtn.addActionListener(e -> {
                if (account1 != null) {
                    int amt = Integer.parseInt(amountField.getText().trim());
                    account1.deposit(amt);
                    statusLabel.setText("Status: Deposited TK " + amt);
                    detailsLabel.setText("Balance: TK " + account1.currentBalance + " | Limit: TK " + account1.transactionLimit);
                } else {
                    statusLabel.setText("Error: Open an account first!");
                }
            });

            withdrawBtn.addActionListener(e -> {
                if (account1 != null) {
                    int amt = Integer.parseInt(amountField.getText().trim());
                    account1.withdraw(amt);
                    statusLabel.setText("Status: Withdraw attempted for TK " + amt);
                    detailsLabel.setText("Balance: TK " + account1.currentBalance + " | Limit: TK " + account1.transactionLimit);
                } else {
                    statusLabel.setText("Error: Open an account first!");
                }
            });

            transferBtn.addActionListener(e -> {
                if (account1 != null) {
                    int amt = Integer.parseInt(amountField.getText().trim());
                    if (amt > 0 && amt < account1.transactionLimit && amt <= account1.currentBalance) {
                        account1.withdraw(amt);
                        account2.deposit(amt);
                        statusLabel.setText("Status: Transferred TK " + amt + " to account 2!");
                        detailsLabel.setText("Balance: TK " + account1.currentBalance + " | Acc2 Bal: TK " + account2.currentBalance);
                    } else {
                        statusLabel.setText("Error: Invalid amount, over limit, or low funds.");
                    }
                } else {
                    statusLabel.setText("Error: Open an account first!");
                }
            });

            // 6. Make the window visible
            frame.setVisible(true);
        }
    }
}
