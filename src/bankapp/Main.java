package bankapp;

public class Main {
    public static void main(String[] args) {
   

        System.out.println("Welcome to the Banking App!");
        
        while (true) {
        	Menu menu = new Menu();
            menu.accountDisplayOptions();
            int choice = menu.getUserInputInt();
            
            if (choice == 1 || choice == 2) {
                menu.processUserAccountMenuChoice(choice);
                if (!menu.getCurrentUser().isEmpty()) {
                    // Successful login
                    System.out.println("Welcome, " + menu.getCurrentUser() + "!");
                    boolean loggedIn = true;
                    System.out.println("Current Balance: " + menu.getAccount().getCurrentBalance());
                    while (loggedIn) {
                        System.out.println("Enter '1' to deposit money, '2' to withdraw money, '3' to view transaction history, '4' to log out:");
                        int action = menu.getUserInputInt();
                        
                        if (action == 1) {
                            menu.displayOptions();
                            double amount = menu.getUserInput();
                            menu.processUserInput(amount);
                            System.out.println("Current balance: " + menu.getAccount().getCurrentBalance());
                        } 
                        else if (action == 2) {
                            System.out.println("Enter an amount to withdraw: ");
                            double amount = menu.getUserInput();
                            menu.getAccount().withdraw(amount);
                            System.out.println("Current balance: " + menu.getAccount().getCurrentBalance());
                        }
                        else if (action == 3) {
                        	menu.getAccount().showTransactionHistory();
                        }
                        else if (action == 4) {
                        	System.out.println("Logging out...");
                        	loggedIn = false;
                        	System.out.println("Successfully Logged out");
                        }
                        else {
                            System.out.println("Invalid choice. Please try again.");
                        }
                    }
                }
            } 
            else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

}
