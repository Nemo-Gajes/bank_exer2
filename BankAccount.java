
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a bank account and its transaction history.
 */
public class BankAccount {

    private double balance;
    private boolean frozen;
    private final String ownerName;
    private final List<Transaction> transactionHistory;

    /**
     * Creates a bank account with an empty owner name.
     */
    public BankAccount() {
        this("");
    }

    /**
     * Creates a bank account for the specified owner.
     *
     * @param ownerName the account owner's name
     */
    public BankAccount(String ownerName) {
        this.balance = 0.0;
        this.frozen = false;
        this.ownerName = ownerName == null ? "" : ownerName;
        this.transactionHistory = new ArrayList<>();
    }

    /**
     * Returns the account owner's name.
     *
     * @return the account owner's name
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * Returns a copy of the transaction history.
     *
     * @return the transaction history
     */
    public List<Transaction> getTransactionHistory() {
        return new ArrayList<>(transactionHistory);
    }

    /**
     * Deposits money into the account.
     *
     * @param amount the amount to deposit
     */
    public void deposit(double amount) {
        if (frozen) {
            System.out.println(
                    "Account is FROZEN. Unable to transact.");
            return;
        }

        if (amount <= 0 || !Double.isFinite(amount)) {
            System.out.println(
                    "Amount deposited: " + String.format("%.2f", amount));
            System.out.println(
                    "Invalid amount. Must be greater than 0");
            return;
        }

        balance += amount;
        transactionHistory.add(
                new Transaction("Deposit", amount));

        System.out.printf("Amount deposited: %.2f%n", amount);
        System.out.printf("Current balance: %.2f%n", balance);
    }

    /**
     * Withdraws money from the account.
     *
     * @param amount the amount to withdraw
     */
    public void withdraw(double amount) {
        if (frozen) {
            System.out.println(
                    "Account is FROZEN. Unable to transact.");
            return;
        }

        if (amount <= 0 || !Double.isFinite(amount)) {
            System.out.printf("Amount withdrawn: %.2f%n", amount);
            System.out.println(
                    "Invalid amount. Must be greater than 0");
            return;
        }

        if (amount > balance) {
            System.out.printf("Amount withdrawn: %.2f%n", amount);
            System.out.printf(
                    "Insufficient balance. Current balance: %.2f%n",
                    balance);
            return;
        }

        balance -= amount;
        transactionHistory.add(
                new Transaction("Withdraw", amount));

        System.out.printf("Amount withdrawn: %.2f%n", amount);
        System.out.printf("Current balance: %.2f%n", balance);
    }

    /**
     * Returns the current balance.
     *
     * @return the current balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Freezes the account.
     */
    public void freezeAccount() {
        frozen = true;
    }

    /**
     * Unfreezes the account.
     */
    public void unfreezeAccount() {
        frozen = false;
    }

    /**
     * Checks whether the account is frozen.
     *
     * @return true if the account is frozen
     */
    public boolean isFrozen() {
        return frozen;
    }
}
