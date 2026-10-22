import java.util.List;

/**
 * Demonstrates bank account operations from the manager's perspective.
 *
 * <p>This program performs hard-coded deposits and withdrawals, tests account
 * validation and security, displays transaction history, and lists managed
 * bank accounts.
 */
public class Main {

    /** Separator marking the beginning and end of the report. */
    private static final String LINE =
            "==================================================";

    /** Separator used between report sections. */
    private static final String DIVIDER =
            "--------------------------------------------------";

    /**
     * Runs the hard-coded bank account demonstration.
     *
     * @param args command-line arguments; not used
     */
    public static void main(String[] args) {
        BankAccountManager manager = new BankAccountManager();
        BankAccount acc1 = new SavingsAccount("Test0");

        System.out.println(LINE);
        System.out.println("             BANK ACCOUNT MANAGER");
        System.out.println(LINE);
        System.out.println("Session: Account Management Demonstration");
        System.out.println("Manager: System Administrator");

        // Display the account overview.
        System.out.println();
        System.out.println(DIVIDER);
        System.out.println("1. ACCOUNT OVERVIEW");
        System.out.println(DIVIDER);
        System.out.println("Account Owner : " + acc1.getOwnerName());
        System.out.printf("Opening Balance: %10.2f%n", acc1.getBalance());

        // Test valid and invalid transactions.
        System.out.println();
        System.out.println(DIVIDER);
        System.out.println("2. TRANSACTION MONITORING");
        System.out.println(DIVIDER);

        System.out.println("[DEPOSIT] Valid transaction");
        acc1.deposit(1000.125);

        System.out.println();
        System.out.println("[DEPOSIT] Zero amount");
        acc1.deposit(0);

        System.out.println();
        System.out.println("[DEPOSIT] Negative amount");
        acc1.deposit(-500);

        System.out.println();
        System.out.println("[WITHDRAWAL] Valid transaction");
        acc1.withdraw(500);

        System.out.println();
        System.out.println("[WITHDRAWAL] Insufficient balance");
        acc1.withdraw(1500);

        System.out.println();
        System.out.println("[WITHDRAWAL] Negative amount");
        acc1.withdraw(-100);

        // Test account freezing and unfreezing.
        System.out.println();
        System.out.println("[SECURITY] Freezing account");
        acc1.freezeAccount();
        acc1.deposit(50);
        acc1.withdraw(50);

        System.out.println();
        System.out.println("[SECURITY] Unfreezing account");
        acc1.unfreezeAccount();
        acc1.withdraw(100);

        // Display the final account status.
        System.out.println();
        System.out.println(DIVIDER);
        System.out.println("3. FINAL ACCOUNT STATUS");
        System.out.println(DIVIDER);
        System.out.println("Account Owner : " + acc1.getOwnerName());
        System.out.printf("Final Balance : %10.2f%n", acc1.getBalance());
        System.out.println(
                "Account Status: "
                        + (acc1.isFrozen() ? "FROZEN" : "ACTIVE"));

        // Display the successful transactions.
        System.out.println();
        System.out.println(DIVIDER);
        System.out.println("4. TRANSACTION HISTORY");
        System.out.println(DIVIDER);

        List<Transaction> history = acc1.getTransactionHistory();

        if (history.isEmpty()) {
            System.out.println("No successful transactions recorded.");
        } else {
            int transactionNumber = 1;

            for (Transaction transaction : history) {
                System.out.printf(
                        "%d. %s%n", transactionNumber, transaction);
                transactionNumber++;
            }
        }

        // Register accounts with the manager.
        System.out.println();
        System.out.println(DIVIDER);
        System.out.println("5. ACCOUNT REGISTRATION");
        System.out.println(DIVIDER);

        manager.addAccount(acc1);
        manager.addAccount(new SavingsAccount("Test1"));

        // Display all accounts managed by the manager.
        System.out.println();
        System.out.println(DIVIDER);
        System.out.println("6. MANAGED ACCOUNTS");
        System.out.println(DIVIDER);

        manager.listAccounts();

        System.out.println();
        System.out.println("Total Accounts Managed: 2");
        System.out.println(LINE);
        System.out.println("       END OF ACCOUNT MANAGEMENT REPORT");
        System.out.println(LINE);
    }
}