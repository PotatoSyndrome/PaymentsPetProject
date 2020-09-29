package org.epam.training.kocherhin.Entity;

public class Account extends Entity {
    private String cardNumber;
    private String name;
    private int amount;
    private Currency currency;
    private long userId;
    private boolean blocked;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    @Override
    public String toString() {
        return "Account{" +
                "cardNumber='" + cardNumber + '\'' +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", currency=" + currency +
                ", userId=" + userId +
                ", blocked=" + blocked +
                '}';
    }

    public enum Currency {UAH, USD, EUR}
}
