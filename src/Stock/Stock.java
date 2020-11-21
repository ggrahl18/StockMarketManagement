package Stock;

import java.time.LocalDate;

public class Stock {
    private static Stock stock = new Stock();

    public String companyName;
    public String ticker;
    final LocalDate creationDate = LocalDate.now();
    public double sharePrice;
    public int outstandingShares;
    public String exchange;
    public String tradingStatus;

    private Stock() { }

    public static Stock getStock() {
        return stock;
    }

    public void setStock(String companyName, String ticker, double sharePrice, int outstandingShares, String exchange, String tradingStatus) {
        this.companyName = companyName;
        this.ticker = ticker;
        this.sharePrice = sharePrice;
        this.outstandingShares = outstandingShares;
        this.exchange = exchange;
         this.tradingStatus = tradingStatus;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getTicker() {
        return ticker;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public double getSharePrice() {
        return sharePrice;
    }

    public int getOutstandingShares() {
        return outstandingShares;
    }

    public String getExchange() {
        return exchange;
    }

    public String getTradingStatus() {
        return tradingStatus;
    }
}
