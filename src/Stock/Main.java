package Stock;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    public static StockFactory stockFactory = new StockFactory();
    public static ExchangeFactory exchangeFactory = new ExchangeFactory();
    public static ArrayList<StockExchange> exchangeList = new ArrayList();

    public static void main(String[] args) {
//       StockExchange exchange = exchangeFactory.getExchange("new");
//       StockExchange pinkExchange = exchange.createStockExchange("pink");
//       Stock stock = stockFactory.getStock("new");
//       stock.setStock("decn", "decn", 0.22, 300000000, "pink", "current");
//       pinkExchange.addStock(stock);
//       pinkExchange.printStockList();

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
//                              goBack takes user to the Stock Menu instead of Main Menu
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

    public static String queryExchange(StockExchange stockExchange) {
        if(findExchange(stockExchange) >= 0) {
            return stockExchange.getName();
        }
        return null;
    }

    public static StockExchange queryExchange(String name) {
        int position = findExchange(name);
        if(position >= 0) {
            System.out.println("queryExchange(): " + name + " query in in process");
            return exchangeList.get(position);
        }
        return null;
    }

    public static int findExchange(StockExchange stockExchange) {
        return exchangeList.indexOf(stockExchange);
    }

    public static int findExchange(String name) {
        for(int i=0; i<exchangeList.size(); i++) {
            StockExchange stockExchange = exchangeList.get(i);
            if(stockExchange.getName().equals(name)) {
                System.out.println("findExchange(): " + stockExchange.getName() + " was found at index: " + i);
                return i;
            }
        }

        return -1;
    }

    public static void printAllStocks() {
        System.out.println("Enter Exchange: ");
        String name = scanner.nextLine();

        StockExchange exchange = queryExchange(name);

        if (exchange != null) {
            System.out.print(exchange.getName()+ " Stock List: ");
            exchange.printStockList();
        } else System.out.println("Incorrect exchange.");
    }

    public static void findStock() {
        System.out.println("Enter Exchange where stock is located: ");
        String exchangeName = scanner.nextLine();
        StockExchange exchange = queryExchange(exchangeName);
        scanner.nextLine();
        System.out.println("Enter Stock ticker: ");
        String stockTicker = scanner.nextLine();

        if(exchange == null) {
            System.out.println(exchangeName + " was not found.");
        }

        Stock stock = findStock(stockTicker, exchange);

        if(!(stock == null)) {
            System.out.println(stock.getTicker() + " was found.");
        }
    }

    public static Stock findStock(String stockTicker, StockExchange exchange) {
        Stock stock = exchange.queryStock(stockTicker);

        if(!(stock == null)) {
            return stock;
        }

        return null;
    }

    public static void printStockInformation() {
        System.out.println("Enter Exchange where stock is located: ");
        String exchangeName = scanner.nextLine();
        StockExchange exchange = queryExchange(exchangeName);
        System.out.println("Enter Stock ticker to print information: ");
        String ticker = scanner.nextLine();
        Stock stock = findStock(ticker, exchange);

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

    public static Stock createStock() {
        System.out.println("Creating new stock.");
        System.out.println("Enter exchange: ");
        String exchange = scanner.nextLine();
        System.out.println("Enter company name: ");
        String companyName = scanner.nextLine();
        System.out.println("Enter ticker, 3 or 4 letters: ");
        String ticker = scanner.nextLine();
        System.out.println("Enter share price to the hundredth place: ");
        double price = scanner.nextDouble();
        System.out.println("Enter amount of shares, whole number: ");
        int amount = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter trading status: ");
        String status = scanner.nextLine();

        Stock stock = stockFactory.getStock("new");

        if(stock.getCompanyName() == companyName) {
            System.out.println(companyName + " already exists.");

        }
        if(stock.getCompanyName() == null) {
            System.out.println(companyName + " does not exist.");
        }

        stock.setStock(companyName, ticker, price, amount, exchange, status);
        StockExchange stockExchange = queryExchange(exchange);
        stockExchange.addStock(stock);
        System.out.println(exchange + ": " + companyName + " (" + ticker + ")" + " has initiated a public offering of " + amount
                + " shares, for $" + price + " dollars per shares.");
        return stock;
    }

    public static void removeStock() {
        System.out.println("Enter Exchange where stock is located: ");
        String exchangeName = scanner.nextLine();
        StockExchange exchange = queryExchange(exchangeName);
        System.out.println("Enter Stock ticker: ");
        String stockTicker = scanner.nextLine();

        Stock stock = findStock(stockTicker, exchange);

        if(stock.getTicker().equalsIgnoreCase(stockTicker)) {
            System.out.println(stock.ticker + " has been removed.");
            exchange.stockList.remove(stock);
        } else System.out.print(stockTicker + "was not removed.");
    }

    public static void removeExchange() {
        System.out.println("Enter Exchange to be removed: ");
        String name = scanner.nextLine();
        StockExchange exchange = queryExchange(name);

        if(!(exchange == null)) {
            exchangeList.remove(exchange);
            System.out.println(name + " was removed.");
        } else System.out.print(name + " was not removed.");
    }
}