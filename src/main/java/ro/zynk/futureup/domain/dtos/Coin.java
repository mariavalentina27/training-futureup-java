package ro.zynk.futureup.domain.dtos;

import ro.zynk.futureup.controllers.responses.CoinResponse;

import javax.persistence.*;

@Entity
@Table(name = "coins")
public class Coin {
    @Id
    @GeneratedValue(generator = "sequence", strategy= GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sequence", allocationSize = 10)
    protected Long id;

    @Column
    private String name;

    @Column
    private Float value;

    public Coin() {
    }

    public Coin(CoinResponse coin) {
        this.name = coin.getName();
        this.value = coin.getValue();;
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
