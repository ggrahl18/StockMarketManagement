package Stock;

import java.util.ArrayList;

public class StockExchange {
    private static StockExchange stockExchange = new StockExchange();
    public ArrayList<Stock> stockList;
    public String name;

    private StockExchange() { }

    public static StockExchange getStockExchange() {
        return stockExchange;
    }

    public StockExchange createStockExchange(String name) {
        StockExchange exchange = stockExchange.getStockExchange();
        exchange.setName(name);
        ArrayList<Stock> stockList = new ArrayList();
        this.stockList = stockList;
        this.name = name;
        return exchange;
    }

    public boolean removeStockExchange(StockExchange stockExchange) {
        if (stockExchange == null) {
            System.out.println("Stock exchange does not exist.");
            return false;
        } else {
            String name = stockExchange.getName();
            stockList.remove(stockExchange);
            System.out.println(name + " was successfully removed.");
            return true;
        }
    }

    public Stock addStock(Stock stock) {
        if (stock == null) {
            System.out.println("Stock doesn't exist");
        }

        this.stockList.add(stock);
        System.out.println(stock.getTicker() + " was added to " + this.name);
        return stock;
    }

    public void printStockList() {
        for(Stock stock : stockList) {
            System.out.print(stock.getTicker() + ", ");
        }
    }

    private int findStock(Stock stock) {
        return this.stockList.indexOf(stock);
    }

    private int findStock(String name) {
        for(int i=0; i<this.stockList.size(); i++) {
            Stock stock = this.stockList.get(i);
            if(stock.getTicker().equalsIgnoreCase(name)) {
                return i;
            }
        }

        return -1;
    }

    public String queryStock(Stock stock) {
        if(findStock(stock) >= 0) {
            return stock.getCompanyName();
        }
        return null;
    }

    public Stock queryStock(String name) {
        int position = findStock(name);
        if(position >= 0) {
            return this.stockList.get(position);
        }

        return null;
    }

    public void setName() {
        this.name = name;
    }

    public ArrayList getStockList() {
        return stockList;
    }

    public void setStockList(ArrayList stockList) {
        this.stockList = stockList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
