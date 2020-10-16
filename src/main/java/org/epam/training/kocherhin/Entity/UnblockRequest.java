package org.epam.training.kocherhin.Entity;

public class UnblockRequest {
    private Account account;
    private Long changedBy;
    private String userLogin;
    private boolean considered;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Long getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(Long changedBy) {
        this.changedBy = changedBy;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public boolean isConsidered() {
        return considered;
    }

    public void setConsidered(boolean considered) {
        this.considered = considered;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UnblockRequest that = (UnblockRequest) o;

        if (isConsidered() != that.isConsidered()) return false;
        if (getAccount() != null ? !getAccount().equals(that.getAccount()) : that.getAccount() != null) return false;
        if (getChangedBy() != null ? !getChangedBy().equals(that.getChangedBy()) : that.getChangedBy() != null)
            return false;
        return getUserLogin() != null ? getUserLogin().equals(that.getUserLogin()) : that.getUserLogin() == null;
    }

    @Override
    public int hashCode() {
        int result = getAccount() != null ? getAccount().hashCode() : 0;
        result = 31 * result + (getChangedBy() != null ? getChangedBy().hashCode() : 0);
        result = 31 * result + (getUserLogin() != null ? getUserLogin().hashCode() : 0);
        result = 31 * result + (isConsidered() ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UnblockRequest{" +
                "account=" + account +
                ", changedBy=" + changedBy +
                ", userLogin='" + userLogin + '\'' +
                ", considered=" + considered +
                '}';
    }
}
