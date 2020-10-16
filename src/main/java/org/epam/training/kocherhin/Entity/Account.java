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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (getAmount() != account.getAmount()) return false;
        if (getUserId() != account.getUserId()) return false;
        if (isBlocked() != account.isBlocked()) return false;
        if (getCardNumber() != null ? !getCardNumber().equals(account.getCardNumber()) : account.getCardNumber() != null)
            return false;
        if (getName() != null ? !getName().equals(account.getName()) : account.getName() != null) return false;
        return getCurrency() == account.getCurrency();
    }

    @Override
    public int hashCode() {
        int result = getCardNumber() != null ? getCardNumber().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + getAmount();
        result = 31 * result + (getCurrency() != null ? getCurrency().hashCode() : 0);
        result = 31 * result + (int) (getUserId() ^ (getUserId() >>> 32));
        result = 31 * result + (isBlocked() ? 1 : 0);
        return result;
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

    public enum Currency {
        UAH(2_844),
        USD(100),
        EUR(86); // todo make this work

        private int value;

        Currency(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static int convert(Currency from, Currency to,int amount) {
            return amount * to.getValue() / from.getValue();
        }
    }
}
