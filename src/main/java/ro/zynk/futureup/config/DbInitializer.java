package ro.zynk.futureup.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.zynk.futureup.domain.dtos.Coin;
import ro.zynk.futureup.domain.repositories.CoinRepository;

import javax.annotation.PostConstruct;

@Component
public class DbInitializer {
    private CoinRepository coinRepository;

    @Autowired
    public DbInitializer(CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
    }

    @PostConstruct
    public void initializer(){
        Coin coinBTC= new Coin("Bitcoin",41657.58);
        coinRepository.save(coinBTC);
        Coin coinETH= new Coin("Ethereum",2894.94);
        coinRepository.save(coinETH);
        Coin coinBNB= new Coin("BNB",393.32);
        coinRepository.save(coinBNB);

    }
}
