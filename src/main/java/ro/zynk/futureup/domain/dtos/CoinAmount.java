package ro.zynk.futureup.domain.dtos;

import javax.persistence.*;

@Entity
@Table(name = "coin_amounts", uniqueConstraints = {@UniqueConstraint(columnNames = {"wallet_id", "coin_id"})})
public class CoinAmount extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @ManyToOne
    @JoinColumn(name = "coin_id")
    private Coin coin;

    private Float amount;

    public CoinAmount() {
    }

    public CoinAmount(Wallet wallet, Coin coin, Float amount) {
        this.wallet = wallet;
        this.coin = coin;
        this.amount = amount;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Coin getCoin() {
        return coin;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
}
