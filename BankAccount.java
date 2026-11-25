package lab_exam1;

public class BankAccount {
    private String holdersName;
    private String accountID;
    private String gender;
    public int currentBalance;
    public int transactionLimit;
    public  BankAccount(String accountID,String holdersName,String gender,int currentBalance){

        this.accountID=accountID;
        this.holdersName=holdersName;
        this.gender=gender;
        this.currentBalance=currentBalance;
        if(gender.equalsIgnoreCase("male")){
            this.transactionLimit=4500;
        }
        else if(gender.equalsIgnoreCase("female")){
            this.transactionLimit=5000;
        }


    }

    public void AccountOpen(String accountID,String holdersName,String gender,int startingDeposit){
        this.accountID=accountID;
        this.holdersName=holdersName;
        this.gender=gender;
        if(startingDeposit<100) {
            this.currentBalance = 0;
            System.out.printf("Your initial deposit is very low!\n");

        }
        else{
            this.currentBalance=startingDeposit;
            System.out.printf("Account opened successfully with %d initial balance\n",startingDeposit);
        }
    }
    public void deposit(int balance){
        if(balance>0 && balance<=20000){
            currentBalance+=balance;
            System.out.printf("TK %d Deposited\n ",balance);
        }
        else {
            System.out.printf("An error amount deposited\n");
        }
    }
    public void withdraw(int balance) {
        if (balance > 0 && balance < transactionLimit && balance <= currentBalance) {
            currentBalance -= balance;
           System.out.printf("Tk %d withdrawn\n",balance);
        } else if (balance < 0) {
            System.out.printf("Invalid amount withdrawn \n");
        } else if (balance > transactionLimit) {
            System.out.printf("Transaction  limit was exceeded \n");
        } else if (balance <= currentBalance) {
            System.out.printf("Insufficient funds \n");
        }
    }
    public void checkBalance(){
        System.out.printf("Now Balance is:%d ",currentBalance);
    }
    public void viewAccountSummary(){
        System.out.printf("Account ID:%s\n",accountID);
        System.out.printf("Account Holders Name:%s\n",holdersName);
        System.out.printf("Gender:%s\n",gender);
        System.out.printf("Current Balance:%d\n",currentBalance);
        System.out.printf("Limit is:%d\n",transactionLimit);
    }










}