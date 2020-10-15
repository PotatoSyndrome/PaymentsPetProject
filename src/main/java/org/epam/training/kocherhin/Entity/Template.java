package org.epam.training.kocherhin.Entity;

public class Template extends Entity {
    private long from;
    private String fromNumber;
    private long to;
    private String toNumber;
    private int amount;
    private Account.Currency currency;

    public long getFrom() {
        return from;
    }

    public void setFrom(long from) {
        this.from = from;
    }

    public String getFromNumber() {
        return fromNumber;
    }

    public void setFromNumber(String fromNumber) {
        this.fromNumber = fromNumber;
    }

    public long getTo() {
        return to;
    }

    public void setTo(long to) {
        this.to = to;
    }

    public String getToNumber() {
        return toNumber;
    }

    public void setToNumber(String toNumber) {
        this.toNumber = toNumber;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Account.Currency getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = Account.Currency.valueOf(currency);
    }

    @Override
    public String toString() {
        return "Template{" +
                "from=" + from +
                ", to=" + to +
                ", amount=" + amount +
                '}';
    }
}
