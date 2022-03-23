package ro.zynk.futureup.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.zynk.futureup.domain.dtos.Coin;
import ro.zynk.futureup.domain.dtos.CoinAmount;
import ro.zynk.futureup.domain.dtos.Wallet;
import ro.zynk.futureup.domain.repositories.CoinAmountRepository;
import ro.zynk.futureup.domain.repositories.CoinRepository;
import ro.zynk.futureup.domain.repositories.WalletRepository;

import javax.annotation.PostConstruct;

@Component
public class DbInitializer {
    private CoinRepository coinRepository;
    private WalletRepository walletRepository;
    private CoinAmountRepository coinAmountRepository;

    @Autowired
    public DbInitializer(CoinRepository coinRepository, WalletRepository walletRepository, CoinAmountRepository coinAmountRepository) {
        this.coinRepository = coinRepository;
        this.walletRepository = walletRepository;
        this.coinAmountRepository = coinAmountRepository;
    }

    @PostConstruct
    public void initializer() {
        Wallet wallet = new Wallet("BigDickEnergy");
        walletRepository.save(wallet);
        Coin coinBTC = new Coin("Bitcoin", 41657.58);
        coinRepository.save(coinBTC);
        Coin coinETH = new Coin("Ethereum", 2894.94);
        coinRepository.save(coinETH);
        Coin coinBNB = new Coin("BNB", 393.32);
        coinRepository.save(coinBNB);
        CoinAmount coinAmountBTC = new CoinAmount(wallet, coinBTC, 2.0000000000001f);
        CoinAmount coinAmountETH = new CoinAmount(wallet, coinETH, 3f);
        CoinAmount coinAmountBNB = new CoinAmount(wallet, coinBNB, 100.2f);
        wallet.getCoinAmounts().add(coinAmountBNB);
        wallet.getCoinAmounts().add(coinAmountBTC);
        wallet.getCoinAmounts().add(coinAmountETH);
        walletRepository.save(wallet);

    }
}
