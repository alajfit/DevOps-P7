package revolut;

import java.util.Currency;

public class Account {
    private Currency accCurrency;
    private double balance;

    public Account(Currency currency, double balance){
        this.balance = balance;
        this.accCurrency = currency;
    }

    public void setBalance(double newBalance) {
        this.balance = newBalance;
    }

    public double getBalance() {
        return this.balance;
    }

    public void addFunds(double topUpAmount) {
        if (topUpAmount > 0) {
            this.balance += topUpAmount;
        } else {
            System.out.println("Failed to Top Up, Negative Balance");
        }
    }

    public void removeFunds(double removeAmount) {
        this.balance -= removeAmount;
    }
}
