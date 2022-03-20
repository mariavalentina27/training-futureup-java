package ro.zynk.futureup.domain.dtos;

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
    private Double value;

    public Coin() {
    }

    public Coin(String name, Double value) {
        this.name = name;
        this.value = value;
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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
