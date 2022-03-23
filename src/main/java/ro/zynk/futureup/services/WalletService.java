package ro.zynk.futureup.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.zynk.futureup.controllers.requests.CoinTransactionRequest;
import ro.zynk.futureup.controllers.responses.CoinResponse;
import ro.zynk.futureup.controllers.responses.CoinTransactionResponse;
import ro.zynk.futureup.controllers.responses.WalletResponse;
import ro.zynk.futureup.domain.dtos.Coin;
import ro.zynk.futureup.domain.dtos.CoinAmount;
import ro.zynk.futureup.domain.dtos.Wallet;
import ro.zynk.futureup.domain.repositories.CoinRepository;
import ro.zynk.futureup.domain.repositories.WalletRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WalletService {
    private WalletRepository walletRepository;
    private CoinRepository coinRepository;

    @Autowired
    public WalletService(WalletRepository walletRepository, CoinRepository coinRepository) {
        this.walletRepository = walletRepository;
        this.coinRepository = coinRepository;
    }

    public WalletResponse saveNewWallet(WalletResponse walletResponse) {
        Wallet wallet = new Wallet(walletResponse);
        wallet = walletRepository.save(wallet);
        return new WalletResponse(wallet);
    }

    public WalletResponse getWallet(Long id) {
        Wallet wallet = walletRepository.getById(id);
        //TODO check if null or else throw exception
        return new WalletResponse(wallet);
    }

    public CoinTransactionResponse buyCoin(CoinTransactionRequest buyCoinRequest) {
        Wallet wallet = walletRepository.getById(buyCoinRequest.getWalletId());
        Coin coin = coinRepository.getById(buyCoinRequest.getCoinId());
        if (coin == null) {
            //TODO throw exception
        }
        if (wallet == null) {
            //TODO throw exception
        }
        CoinAmount coinAmount = new CoinAmount(wallet, coin, buyCoinRequest.getAmount());
        wallet.getCoinAmounts().add(coinAmount);
        walletRepository.save(wallet);
        return new CoinTransactionResponse(new CoinResponse(coin), new WalletResponse(wallet), coinAmount.getAmount());
    }

    public List<CoinTransactionResponse> getAllCoinsFromWallet(Long walletId) {
        Wallet wallet=walletRepository.getById(walletId);
        if(wallet==null){
            //TODO add exception
        }
        return wallet.getCoinAmounts().stream().map(CoinTransactionResponse::new).collect(Collectors.toList());
    }
}
