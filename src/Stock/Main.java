package Stock;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    public static StockFactory stockFactory = new StockFactory();
    public static ExchangeFactory exchangeFactory = new ExchangeFactory();
    public static ArrayList<StockExchange> exchangeList = new ArrayList();

    public static void main(String[] args) {

        int choice = 0;
        boolean quit = false;

        printMainMenu();
        while(!quit) {
            System.out.println("Enter action: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0:
                    System.out.println("\nQuiting...");
                    quit = true;
                    break;
                case 1:
                    printExchangeMenu();

                    boolean goBack = false;
                    int nextChoice = 0;

                    while(!goBack) {
                        System.out.println("Enter action: ");
                        nextChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch (nextChoice) {
                            case 0:
                                goBack = true;
//                              goBack works, but it takes entering '0' twice
                                break;
                            case 1:
                                createStockExchange();
                                break;
                            case 2:
                                System.out.println("Enter Exchange: ");
                                String name = scanner.nextLine();
                                findExchange(name);
                                break;
                            case 3:
                                printAllStocks();
                                break;
                            case 4:
                                removeExchange();
                                break;
                        }
                    }
                case 2:
                    printStockMenu();

                    boolean goBackTwo = false;
                    int otherChoice = 0;

                    while(!goBackTwo) {
//                  goBackTwo - naming choice is poor ???
                        System.out.println("Enter action: ");
                        otherChoice = scanner.nextInt();
                        scanner.nextLine();
                        switch (otherChoice) {
                            case 0:
                                goBackTwo = true;
//                              goBack works, but it takes entering '0' twice
                                printMainMenu();
                                break;
                            case 1:
                                createStock();
                                break;
                            case 2:
                                findStock();
                                break;
                            case 3:
                                printStockInformation();
                                break;
                            case 4:
                                removeStock();
                                break;
                        }
                    }
            }
        }
    }

    public static void printMainMenu() {
        System.out.println("***Stock & Exchange Management Platform***");
        System.out.println("Main Menu");
        System.out.println("#0 - Quit");
        System.out.println("#1 - Exchange Menu");
        System.out.println("#2 - Stock Menu");
    }

    public static void printExchangeMenu() {
        System.out.println("*Exchange Management*");
        System.out.println("#0 - Go Back");
        System.out.println("#1 - Create Exchange");
        System.out.println("#2 - Find Exchange");
        System.out.println("#3 - Print all stocks in the Exchange");
        System.out.println("#4 - Remove Exchange");
    }

    public static void printStockMenu() {
        System.out.println("*Stock Management*");
        System.out.println("#0 - Go Back");
        System.out.println("#1 - Create Stock");
        System.out.println("#2 - Find Stock");
        System.out.println("#3 - Print Stock Information");
        System.out.println("#4 - Remove Stock");
    }

    public static void createStockExchange() {
        System.out.println("Enter Exchange name: ");
        String name = scanner.nextLine();
        StockExchange exchange =  exchangeFactory.getExchange("new");

        if(exchange.getName() == name) {
            System.out.println("Exchange already exists.");
        } else if (exchange.getName() == null) {
            exchange.createStockExchange(name);
            exchangeList.add(exchange);
        }
    }

    private static int queryExchange(String name) {
        for(int i=0; i<exchangeList.size(); i++) {
            if(exchangeList.get(i).getName() == name) {
                int position = exchangeList.indexOf(i);
                return position;
            }
        }

        return -1;
    }

    public static StockExchange findExchange(String name) {
        int position = queryExchange(name);
        StockExchange exchange = findExchange(position);
        System.out.println(exchange.getName() + " was found at index: " + position);
        return exchange;
    }

    public static StockExchange findExchange(int position) {
//        IndexOutOfBoundsException - need to fix
        StockExchange exchange = exchangeList.get(position);

        if(exchange == null) {
            System.out.println("Exchange not found.");
            return null;
        }

        return exchange;
    }

    public static void printAllStocks() {
        System.out.println("Enter Exchange: ");
        String name = scanner.nextLine();

        int position = queryExchange(name);
        StockExchange exchange = findExchange(position);

        if(exchange != null) {
            exchange.printStockList();
        } else System.out.println("Incorrect exchange.");
    }

    public static Stock findStock() {
        System.out.println("Enter Exchange where stock is located: ");
        String exchangeName = scanner.nextLine();
        StockExchange exchange = findExchange(exchangeName);
        scanner.nextLine();
        System.out.println("Enter Stock ticker: ");
        String stockTicker = scanner.nextLine();

        for(int i=0; i<exchange.stockList.size(); i++) {
            Stock stock = exchange.stockList.get(i);
            if(stock.getTicker() == stockTicker) {
                return stock;
            }
        }

        return null;
    }

    public static Stock findStock(String stockTicker, String exchangeName) {
        StockExchange exchange = findExchange(exchangeName);
        System.out.println("Enter Stock ticker: ");

        for(int i=0; i<exchange.stockList.size(); i++) {
            Stock stock = exchange.stockList.get(i);
            if(stock.getTicker() == stockTicker) {
                return stock;
            }
        }

        return null;
    }

    public static void printStockInformation() {
        System.out.println("Enter Exchange where stock is located: ");
        String exchangeName = scanner.nextLine();
        StockExchange exchange = findExchange(exchangeName);
        System.out.println("Enter Stock ticker to print information: ");
        String ticker = scanner.nextLine();
        Stock stock = findStock(ticker, exchangeName);

        System.out.println(
                "***Stock Information***" + "\n"
                        + "Company name: " + stock.companyName + "\n"
                        + "Ticker: " + stock.getTicker() + "\n"
                        + "Created: " + stock.creationDate + "\n"
                        + "Share Price: " + stock.sharePrice + "\n"
                        + "Outstanding Shares: " + stock.outstandingShares + "\n"
                        + "Current Exchange: " + stock.exchange + "\n"
                        + "Trading Status: " + stock.tradingStatus
        );
    }

    public static void createStock() {
        System.out.println("Creating new stock.");
        System.out.println("Enter company name: ");
        String companyName = scanner.nextLine();
        System.out.println("Enter ticker, 3 or 4 letters: ");
        String ticker = scanner.nextLine();
        System.out.println("Enter share price to the hundredth place: ");
        double price = scanner.nextDouble();
        System.out.println("Enter amount of shares, whole number: ");
        int amount = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter exchange: ");
        String exchange = scanner.nextLine();
        System.out.println("Enter trading status: ");
        String status = scanner.nextLine();

        Stock stock = stockFactory.getStock("new");
        if(stock.getCompanyName() == companyName) {
            System.out.println(companyName + " already exists.");
        } else if (stock.getCompanyName() == null) {
            stock.setStock(companyName, ticker, price, amount, exchange, status);
            System.out.println(companyName + " (" + ticker + ")" + " has initiated a public offering of " + amount
                    + " shares, for $" + amount + " dollars per shares.");
        }

        printStockMenu();
    }

    public static void removeStock() {
        System.out.println("Enter Exchange where stock is located: ");
        String exchangeName = scanner.nextLine();
        StockExchange exchange = findExchange(exchangeName);
        System.out.println("Enter Stock ticker: ");
        String stockTicker = scanner.nextLine();

        for(int i=0; i<exchange.stockList.size(); i++) {
            Stock stock = exchange.stockList.get(i);
            if(stock.getTicker() == stockTicker) {
                System.out.println(stock.ticker + " has been removed.");
                exchange.stockList.remove(stock);
                break;
            }
        }
    }

    public static void removeExchange() {
        System.out.println("Enter Exchange to be removed: ");
        String name = scanner.nextLine();
        int position = queryExchange(name);
        if(!(position < 0)) {
            exchangeList.remove(position);
            System.out.println(name + " was removed.");
        }
    }
}