package Stock;

public class ExchangeFactory {

    public StockExchange getExchange(String stockExchange) {
        if(stockExchange == null) {
            return null;
        } else if(stockExchange == "new") {
            System.out.println("***Stock Exchange Factory***");
            return StockExchange.getStockExchange();
        }
        return null;
    }
}
