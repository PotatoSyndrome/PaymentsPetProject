package org.epam.training.kocherhin.Entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Payment extends Entity {

    private static final SimpleDateFormat SDF =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private long from;
    private long to;
    private int amount;
    private Status status;
    private Date time; /// TODO predict problems with inserts, use String

    public long getFrom() {
        return from;
    }

    public void setFrom(long from) {
        this.from = from;
    }

    public long getTo() {
        return to;
    }

    public void setTo(long to) {
        this.to = to;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status.toString();
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
