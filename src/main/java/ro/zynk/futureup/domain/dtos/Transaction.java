package ro.zynk.futureup.domain.dtos;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "transactions")
public class Transaction extends BaseEntity {

    @Column
    private Date transactionDate;

    @ManyToOne
    @JoinColumn(name = "coin_id")
    private Coin coin;

    @Column
    private Double amount;

    @Column
    private float totalValue;

    public Transaction(Date date, Coin coin, Double amount, Float totalValue) {
        this.transactionDate = date;
        this.coin = coin;
        this.amount = amount;
        this.totalValue = totalValue;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date date) {
        this.transactionDate = date;
    }

    public Coin getCoin() {
        return coin;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Float getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Float totalValue) {
        this.totalValue = totalValue;
    }
}
