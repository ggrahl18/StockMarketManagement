package Stock;

public class ExchangeFactory {

    public StockExchange getExchange(String stockExchange) {
        if(stockExchange == null) {
            return null;
        } else if(stockExchange == "new") {
            System.out.println("New stock exchange initiated.");
            return StockExchange.getStockExchange();
        }
        return null;
    }
}
