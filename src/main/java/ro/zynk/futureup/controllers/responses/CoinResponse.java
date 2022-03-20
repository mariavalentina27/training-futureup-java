package ro.zynk.futureup.controllers.responses;

import ro.zynk.futureup.domain.dtos.Coin;

import java.io.Serializable;

public class CoinResponse implements Serializable {
    private Long id;
    private String name;
    private Float value;

    public CoinResponse(Coin coin) {
        this.id = coin.getId();
        this.name = coin.getName();
        this.value = coin.getValue();
    }

    public CoinResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }
}
