package Stock;

import java.util.ArrayList;
import java.util.HashMap;

public class Person {
    private static Person person = new Person();
    public String name;
    public double currentBalance;
    public ArrayList<Stock> watchList;
    public HashMap<Stock, Integer> stocksOwned;
    public ArrayList<Stock> stocksOwnedList;

    private Person() {
        this.currentBalance = 0;
        this.watchList = new ArrayList<Stock>();
        this.stocksOwned = new HashMap<Stock, Integer>();
        this.stocksOwnedList = new ArrayList<Stock>();
    }

    public void printStocksOwned() {
        System.out.println("Need to figure out how to print these...");
//        when buying stocks, need to add the key to an ArrayList
//        then the Map of owned stocks and quantity can be remembered/accessed
//        and iterated through to print ownedStocks
    }

    public void addToWatchlist(Stock stock) {
        if(stock.equals(null)) {
            System.out.println("Incorrect stock.");
        }

        if(this.watchList.contains(stock)) {
            System.out.println("Watchlist contains stocks.");
        }

        watchList.add(stock);
        System.out.println(stock.getTicker() + " added to watchlist.");
    }

    public void removeFromWatchlist(Stock stock) {
        if(stock == null) {
            System.out.println("Incorrect stock.");
        }

        if(this.watchList.contains(stock)) {
            watchList.remove(stock);
            System.out.println(stock.getTicker() + " removed from watchlist.");
        }
    }

    public Stock printWatchList() {
        for(int i=0; i<watchList.size(); i++) {
            return watchList.get(i);
        }

        return null;
    }

    public double addFunds(double amount) {
        if(amount > 0) {
            System.out.println(amount + " added to " + this.name);
            System.out.println("Current Balance: " + this.currentBalance);
            return this.currentBalance += amount;
        }
        System.out.println("Deposit Error");
        return this.currentBalance;
    }

    public void withdrawFunds(double amount) {
        if(amount >= this.currentBalance && amount <= 0) {
            this.currentBalance -= amount;
            System.out.println("Amount withdrawn: $" + amount);
            System.out.println("Current Balance: $" + this.currentBalance);
        } else
        System.out.println("Insufficient funds to withdraw.");
    }

    public boolean buyStock(Stock stock, int quantity) {
//        make sure this.stocksOwnedList works properly with this.stocksOwned
        if(!(stock == null)) {
            double cost = stock.sharePrice * quantity;
            System.out.println("Prior Balance: " + this.currentBalance);
            System.out.println("Attempting to buy: " + stock.getTicker() + " x " + quantity + " = " + cost);
            if(cost < this.currentBalance && this.currentBalance > 0) {
                this.stocksOwned.put(stock, quantity);
                this.stocksOwnedList.add(stock);
                this.currentBalance -= cost;
                System.out.println("Successfully Purchased: " + stock.getTicker() + " x " + quantity + " = " + cost);
                System.out.println("Current Balance: " + this.currentBalance);
                return true;
            }
        }

        System.out.println("Transaction did not complete. Please transfer for funds to continue.");
        return false;
    }

    public void sellStock(Stock stock, int quantity) {
        if(stocksOwned.containsKey(stock)) {
            int ownedQuantity = stocksOwned.get(stock);
            if(ownedQuantity >= quantity) {
                ownedQuantity -= quantity;
                double cost = quantity * stock.sharePrice;
                this.currentBalance += cost;
                this.stocksOwnedList.remove(stock);
                System.out.println(stock.getTicker() + " - " + quantity + " x " + stock.sharePrice + " sold: " + cost);
                System.out.println("Current Balance: " + this.currentBalance);
                if(ownedQuantity == 0) {
                    stocksOwned.remove(stock, 0);
                }
            }
            if(ownedQuantity < quantity) {
                System.out.println("Insufficient quantity of shares.");
            }
        } else System.out.println("Selling error.");
    }

    public static Person getPerson() {
        return person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }
}
