package org.epam.training.kocherhin.Entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Payment extends Entity {

    private static final SimpleDateFormat SDF =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private long from;
    private String fromNumber;
    private long to;
    private String toNumber;
    private int amount;
    private Account.Currency currency;
    private Status status;
    private Date time; /// TODO predict problems with inserts, use String

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

    public String getStatus() {
        if (status != null) {
            return status.toString();
        }
        return "";
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setTime(String time) {
        try {
            this.time = SDF.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        if (getFrom() != payment.getFrom()) return false;
        if (getTo() != payment.getTo()) return false;
        if (getAmount() != payment.getAmount()) return false;
        if (getFromNumber() != null ? !getFromNumber().equals(payment.getFromNumber()) : payment.getFromNumber() != null)
            return false;
        if (getToNumber() != null ? !getToNumber().equals(payment.getToNumber()) : payment.getToNumber() != null)
            return false;
        if (getCurrency() != payment.getCurrency()) return false;
        if (getStatus() != payment.getStatus()) return false;
        return getTime() != null ? getTime().equals(payment.getTime()) : payment.getTime() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getFrom() ^ (getFrom() >>> 32));
        result = 31 * result + (getFromNumber() != null ? getFromNumber().hashCode() : 0);
        result = 31 * result + (int) (getTo() ^ (getTo() >>> 32));
        result = 31 * result + (getToNumber() != null ? getToNumber().hashCode() : 0);
        result = 31 * result + getAmount();
        result = 31 * result + (getCurrency() != null ? getCurrency().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getTime() != null ? getTime().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "from=" + from +
                ", to=" + to +
                ", amount=" + amount +
                ", status=" + status +
                ", time=" + time +
                '}';
    }

    public enum Status {READY, SENT, DECLINED}
}
