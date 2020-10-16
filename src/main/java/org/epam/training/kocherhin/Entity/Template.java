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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Template template = (Template) o;

        if (getFrom() != template.getFrom()) return false;
        if (getTo() != template.getTo()) return false;
        if (getAmount() != template.getAmount()) return false;
        if (getFromNumber() != null ? !getFromNumber().equals(template.getFromNumber()) : template.getFromNumber() != null)
            return false;
        if (getToNumber() != null ? !getToNumber().equals(template.getToNumber()) : template.getToNumber() != null)
            return false;
        return getCurrency() == template.getCurrency();
    }

    @Override
    public int hashCode() {
        int result = (int) (getFrom() ^ (getFrom() >>> 32));
        result = 31 * result + (getFromNumber() != null ? getFromNumber().hashCode() : 0);
        result = 31 * result + (int) (getTo() ^ (getTo() >>> 32));
        result = 31 * result + (getToNumber() != null ? getToNumber().hashCode() : 0);
        result = 31 * result + getAmount();
        result = 31 * result + (getCurrency() != null ? getCurrency().hashCode() : 0);
        return result;
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
