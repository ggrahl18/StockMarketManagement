 package Stock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    public static PeopleFactory peopleFactory = new PeopleFactory();
    public static PersonFactory personFactory= new PersonFactory();
    public static StockFactory stockFactory = new StockFactory();
    public static ExchangeFactory exchangeFactory = new ExchangeFactory();
    public static ArrayList<StockExchange> exchangeList = new ArrayList();
    public static HashMap<String, GroupOfPeople> groupOfPeopleMap = new HashMap<String, GroupOfPeople>();


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
                    int firstChoice = 0;

                    while(!goBack) {
                        System.out.println("Enter action: ");
                        firstChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch (firstChoice) {
                            case 0:
                                goBack = true;
//                              goBack works, but it takes you to the Stock Menu
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
                    int secondChoice = 0;

                    while(!goBackTwo) {
//                  goBackTwo - naming choice is poor ???
                        System.out.println("Enter action: ");
                        secondChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch (secondChoice) {
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
                case 3:
                    printPersonActions();

                    boolean goBackThree = false;
                    int thirdChoice = 0;

                    while(!goBackThree) {
//                  goBackThree - naming choice is poor ???
                        System.out.println("Enter action: ");
                        thirdChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch (thirdChoice) {
                            case 0:
                                goBackThree = true;
                                printMainMenu();
                                break;
                            case 1:
                                showPerson();
                                break;
                            case 2:
                                buyStock();
                                break;
                            case 3:
                                sellStock();
                                break;
                            case 4:
                                printStockInformation();
                            case 5:
                                addToWatchlist();
                                break;
                            case 7:
                                removeFromWatchlist();
                                break;
                            case 8:
                                depositFunds();
                                break;
                            case 9:
                                withdrawFunds();
                                break;
//                          case 10:
//                          create/remove (new) watch lists, only one watch list for now
                        }
                    }
                case 4:
                    printPersonMenu();

                    boolean goBackFourth = false;
                    int fourthChoice = 0;

                    while(!goBackFourth) {
//                  goBackFourth - naming choice is poor ???
                        System.out.println("Enter action: ");
                        fourthChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch (fourthChoice) {
                            case 0:
                                goBackFourth = true;
                                printMainMenu();
                                break;
                            case 1:
                                showAllPersons();
                                break;
                            case 2:
                                createPerson();
                                break;
                            case 3:
                                removePerson();
                                break;
                            case 4:
                                modifyPerson();
                                break;
                            case 5:
                                showPerson();
                                break;
                            case 6:
                                createPeopleList();
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

    public static void printPersonActions() {
        System.out.println("*Person Management*");
        System.out.println("#0 - Go Back");
        System.out.println("#1 - Print Account Information");
        System.out.println("#2 - Buy Stock");
        System.out.println("#3 - Sell Stock");
        System.out.println("#4 - Print Stock Information");
        System.out.println("#5 - Add To Watchlist");
        System.out.println("#6 - Remove from Watchlist");
        System.out.println("#7 - Transfer Money");
        System.out.println("#8 - Withdraw Money");
//        Create/remove watch lists
    }

    public static void printPersonMenu() {
        System.out.println("*Person Management*");
        System.out.println("#0 - Go Back");
        System.out.println("#1 - Show All Persons");
        System.out.println("#2 - Create Person");
        System.out.println("#3 - Remove Person");
        System.out.println("#4 - Modify Person");
        System.out.println("#5 - Show Person");
        System.out.println("#6 - Create Persons List");
    }

    public static void createStockExchange() {
        System.out.println("Enter Exchange name: ");
        String name = scanner.nextLine();
        StockExchange exchange =  exchangeFactory.getExchange("new");

        if(exchange.getName().equals(name)) {
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

        assert exchange != null;
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
        assert exchange != null;
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

    public static void createStock() {
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
        assert stockExchange != null;
        stockExchange.addStock(stock);
        System.out.println(exchange + ": " + companyName + " (" + ticker + ")" + " has initiated a public offering of " + amount
                + " shares, for $" + price + " dollars per shares.");
    }

    public static void removeStock() {
        System.out.println("Enter Exchange where stock is located: ");
        String exchangeName = scanner.nextLine();
        StockExchange exchange = queryExchange(exchangeName);
        System.out.println("Enter Stock ticker: ");
        String stockTicker = scanner.nextLine();

        assert exchange != null;
        Stock stock = findStock(stockTicker, exchange);

        assert stock != null;
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

    public static void showAllPersons() {
        System.out.println("Enter group of people's name: ");
        String name = scanner.nextLine();
        GroupOfPeople groupOfPeople = findGroupOfPeople(name);

        System.out.println(groupOfPeople.showAllPersons().getName() + ", ");
    }

    public static void createPeopleList() {
        System.out.println("Enter persons list: ");
        String name = scanner.nextLine();
        GroupOfPeople people = peopleFactory.getPeople("new");

        if (people.getName().equals(name)) {
            System.out.println(people.getName() + " already exists.");
        } else if (people.getName() == null) {
            people.createPeople(name);
            groupOfPeopleMap.put(people.getName(), people);
            System.out.println(people.getName() + " has been created as a group of people.");
        }
    }

    public static GroupOfPeople findGroupOfPeople() {
        System.out.println("Enter group of people: ");
        String name = scanner.nextLine();

        return groupOfPeopleMap.get(name);
    }

    public static GroupOfPeople findGroupOfPeople(String name) {
        GroupOfPeople groupOfPeople = groupOfPeopleMap.get(name);
        if(groupOfPeople == null) {
            System.out.println("That group does not exist");
        }

        return groupOfPeople;
    }

    public static void createPerson() {
        System.out.println("Enter group of people to add person to: ");
        String groupName = scanner.nextLine();
        System.out.println("Enter person name: ");
        String name = scanner.nextLine();

        Person person = personFactory.getPerson("new");
        person.setName(name);
        System.out.print(person.getName() + " created.");

        GroupOfPeople groupOfPeople = findGroupOfPeople(groupName);
        groupOfPeople.personsList.add(person);
        System.out.println(groupOfPeople.getName() + " has added " + person.getName());
    }

    public static void removePerson() {
        System.out.println("Enter group of people to remove person from: ");
        String groupName = scanner.nextLine();
        System.out.println("Enter person name: ");
        String name = scanner.nextLine();

        GroupOfPeople groupOfPeople = findGroupOfPeople(groupName);
        Person person = groupOfPeople.queryPerson(name);
        groupOfPeople.personsList.remove(person);
    }

    public static void modifyPerson() {
        System.out.println("This method doesn't work yet.");
    }

    public static void showPerson() {
        System.out.println("Enter group of people the person trades under: ");
        String groupName = scanner.nextLine();
        System.out.println("Enter person name: ");
        String personName = scanner.nextLine();

        GroupOfPeople groupOfPeople = findGroupOfPeople(groupName);
        Person person = groupOfPeople.queryPerson(personName);
        System.out.println(person.getName());
        System.out.println(person.getCurrentBalance());
        System.out.println("Stocks Owned: ");
        person.printStocksOwned();
        System.out.println();
        System.out.println("Watch List: ");
        System.out.print(person.printWatchList() + ", ");
        System.out.println();
    }

    public static void buyStock() {
        System.out.println("Enter investors group: ");
        String groupName = scanner.nextLine();
        System.out.println("Enter person name: ");
        String personName = scanner.nextLine();
        System.out.println("Enter stocks exchange: ");
        String exchangeName = scanner.nextLine();
        System.out.println("Enter stock ticker to buy: ");
        String stockTicker = scanner.nextLine();
        System.out.println("Enter quantity of stock to buy: ");
        int quantity = scanner.nextInt();

        GroupOfPeople groupOfPeople = findGroupOfPeople(groupName);
        Person person = groupOfPeople.queryPerson(personName);
        StockExchange exchange = queryExchange(exchangeName);

        assert exchange != null;
        Stock stock = findStock(stockTicker, exchange);

        assert stock != null;
        person.buyStock(stock, quantity);

        System.out.println(personName + " bought " + quantity + " x " +
                stockTicker + " for " + "$" + (quantity * stock.sharePrice));
        System.out.println("Current Balance: " + person.currentBalance);
    }

    public static void sellStock() {
        System.out.println("Enter investors group: ");
        String groupName = scanner.nextLine();
        System.out.println("Enter person name: ");
        String personName = scanner.nextLine();
        System.out.println("Enter stocks exchange: ");
        String exchangeName = scanner.nextLine();
        System.out.println("Enter stock ticker to sell: ");
        String stockTicker = scanner.nextLine();
        System.out.println("Enter quantity of stock to sell: ");
        int quantity = scanner.nextInt();

        GroupOfPeople groupOfPeople = findGroupOfPeople(groupName);
        Person person = groupOfPeople.queryPerson(personName);
        StockExchange exchange = queryExchange(exchangeName);
        assert exchange != null;
        Stock stock = findStock(stockTicker, exchange);

        person.sellStock(stock, quantity);

        assert stock != null;
        System.out.println(personName + " sold " + quantity + " x " +
                stockTicker + " for " + "$" + (quantity * stock.sharePrice));
        System.out.println("Current Balance: " + person.currentBalance);
    }

    public static void addToWatchlist() {
        System.out.println("Enter investors group: ");
        String groupName = scanner.nextLine();
        System.out.println("Enter person name: ");
        String personName = scanner.nextLine();
        System.out.println("Enter stocks exchange: ");
        String exchangeName = scanner.nextLine();
        System.out.println("Enter stock ticker: ");
        String stockTicker = scanner.nextLine();

        GroupOfPeople groupOfPeople = findGroupOfPeople(groupName);
        Person person = groupOfPeople.queryPerson(personName);
        StockExchange exchange = queryExchange(exchangeName);

        assert exchange != null;
        Stock stock = findStock(stockTicker, exchange);

        assert stock != null;
        person.addToWatchlist(stock);
        System.out.println(stock.getTicker() + " added to " + person.getName() + " watchlist.");
    }

    public static void removeFromWatchlist() {
        System.out.println("Enter investors group: ");
        String groupName = scanner.nextLine();
        System.out.println("Enter person name: ");
        String personName = scanner.nextLine();
        System.out.println("Enter stocks exchange: ");
        String exchangeName = scanner.nextLine();
        System.out.println("Enter stock ticker: ");
        String stockTicker = scanner.nextLine();

        GroupOfPeople groupOfPeople = findGroupOfPeople(groupName);
        Person person = groupOfPeople.queryPerson(personName);
        StockExchange exchange = queryExchange(exchangeName);
        assert exchange != null;
        Stock stock = findStock(stockTicker, exchange);

        person.removeFromWatchlist(stock);
        if(person.watchList.contains(stock)) {
            System.out.println("Stock not removed from watchlist.");
        } else {
            assert stock != null;
            System.out.println(stock.getTicker() + " removed from " + person.getName() + " watchlist.");
        }
    }

    public static void depositFunds() {
        System.out.println("Enter investors group: ");
        String groupName = scanner.nextLine();
        System.out.println("Enter person name: ");
        String personName = scanner.nextLine();
        System.out.println("Enter amount to transfer: ");
        double amount = scanner.nextInt();

        GroupOfPeople groupOfPeople = findGroupOfPeople(groupName);
        Person person = groupOfPeople.queryPerson(personName);
        double previousBalance = person.currentBalance;
        person.addFunds(amount);
        if(person.currentBalance > previousBalance) {
            System.out.println("Transfer successful.");
            System.out.println("New Balance: $" + person.currentBalance);
        }
    }

    public static void withdrawFunds() {
        System.out.println("Enter investors group: ");
        String groupName = scanner.nextLine();
        System.out.println("Enter person name: ");
        String personName = scanner.nextLine();
        System.out.println("Enter amount to withdraw: ");
        double amount = scanner.nextInt();

        GroupOfPeople groupOfPeople = findGroupOfPeople(groupName);
        Person person = groupOfPeople.queryPerson(personName);
        person.withdrawFunds(amount);
    }
}