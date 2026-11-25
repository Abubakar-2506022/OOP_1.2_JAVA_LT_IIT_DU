package lab_exam1;
import java.util.Scanner;
public class AccountManagement {

        public static void main(String[] args){
        BankAccount account1=new BankAccount("020001938","Anmona","female",1000);
        BankAccount account2=new BankAccount("024351938","AbuBakar","male",5000);
        account1.AccountOpen("020001938","Anmona","female",1000);
        account1.viewAccountSummary();
        account1.deposit(2000);
        account1.withdraw(1000);
        account1.checkBalance();
        account1.viewAccountSummary();
        //transfering money from account1 to account2
        System.out.printf("Do you want to transfer funds(Y/N):");
        Scanner input=  new Scanner(System.in);
        String reply=input.nextLine();
        if(reply.equalsIgnoreCase("Y")) {
            System.out.printf("What amounts of money you want to transfer from account 1 to account 2:");

            int amount = input.nextInt();
                if (amount > 0 && amount < account1.transactionLimit && amount <= account1.currentBalance) {
                        account1.withdraw(amount);
                        account2.deposit(amount);
                }
                else if (amount < 0) {
                        System.out.printf("Invalid amount transferred \n");
                } else if (amount > account1.transactionLimit) {
                        System.out.printf("Transaction  limit was exceeded \n");
                } else if (amount <= account1.currentBalance) {
                        System.out.printf("Insufficient funds \n");
                }
        }
        account1.checkBalance();
        account1.viewAccountSummary();
        account2.checkBalance();
        account2.viewAccountSummary();




    }


}


