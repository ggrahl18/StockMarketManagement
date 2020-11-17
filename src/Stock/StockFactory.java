package Stock;

public class StockFactory {

    public Stock getStock(String stock) {
        if(stock == null) {
            return null;
        } else if(stock == "new") {
            System.out.println("Initial Public Offering initiated.");
            return Stock.getStock();
        }
        return null;
    }
}
