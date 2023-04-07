package ro.zynk.futureup.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.zynk.futureup.controllers.requests.CoinExchangeRequest;
import ro.zynk.futureup.controllers.requests.CoinTransactionRequest;
import ro.zynk.futureup.controllers.responses.*;
import ro.zynk.futureup.domain.dtos.Coin;
import ro.zynk.futureup.domain.dtos.CoinAmount;
import ro.zynk.futureup.domain.dtos.Transaction;
import ro.zynk.futureup.domain.dtos.Wallet;
import ro.zynk.futureup.domain.repositories.CoinAmountRepository;
import ro.zynk.futureup.domain.repositories.CoinRepository;
import ro.zynk.futureup.domain.repositories.TransactionRepository;
import ro.zynk.futureup.domain.repositories.WalletRepository;
import ro.zynk.futureup.exceptions.NotEnoughFundsException;
import ro.zynk.futureup.exceptions.NotFoundException;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WalletService {
    private final WalletRepository walletRepository;
    private final CoinRepository coinRepository;
    private final CoinAmountRepository coinAmountRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public WalletService(WalletRepository walletRepository, CoinRepository coinRepository, CoinAmountRepository coinAmountRepository, TransactionRepository transactionRepository) {
        this.walletRepository = walletRepository;
        this.coinRepository = coinRepository;
        this.coinAmountRepository = coinAmountRepository;
        this.transactionRepository = transactionRepository;
    }

    public WalletResponse saveNewWallet(WalletResponse walletResponse) {
        Wallet wallet = new Wallet(walletResponse);
        wallet = walletRepository.save(wallet);
        return new WalletResponse(wallet);
    }

    public WalletResponse getWallet(Long id) throws NotFoundException {
        Optional<Wallet> walletOpt = walletRepository.findById(id);
        if (walletOpt.isEmpty()) {
            throw new NotFoundException("Wallet not found!");
        }
        Wallet wallet = walletOpt.get();
        return new WalletResponse(wallet);
    }

    public ListWalletResponse getWallets() {
        List<Wallet> wallets = walletRepository.findAll();

        List<WalletResponse> walletResponses = new ArrayList<>();
        for (Wallet w :
                wallets) {
            walletResponses.add(new WalletResponse(w));
        }

        return new ListWalletResponse(walletResponses);
    }

    public CoinTransactionResponse buyCoin(CoinTransactionRequest buyCoinRequest) throws NotFoundException {
        Optional<Wallet> walletOpt = walletRepository.findById(buyCoinRequest.getWalletId());
        Optional<Coin> coinOpt = coinRepository.findById(buyCoinRequest.getCoinId());
        if (coinOpt.isEmpty()) {
            throw new NotFoundException("Coin not found!");
        }
        if (walletOpt.isEmpty()) {
            throw new NotFoundException("Wallet not found!");
        }

        Coin coin = coinOpt.get();
        Wallet wallet = walletOpt.get();

        /* Assignment 2 */
        ///find the total USD value for this transaction
        float totalValue = transactionRepository.getTotalTransactionValue(buyCoinRequest, coin);
        ///set the date variable to the current date
        Date date = new Date(System.currentTimeMillis());
        ///create the transaction variable which contains the date, the coin that is bought, the number of coins - amount and the total USD value
        Transaction transaction;
        transaction = new Transaction(date, coin, buyCoinRequest.getAmount(), totalValue);
        ///save the new transaction in the transaction database using the save method
        transactionRepository.save(transaction);

        // find existing coin amount to update
        CoinAmount coinAmount = getOrCreateCoinAmount(coin, wallet);
        coinAmount.setAmount(coinAmount.getAmount() + buyCoinRequest.getAmount());

        coinAmountRepository.save(coinAmount);
        return new CoinTransactionResponse(new CoinResponse(coin), new WalletResponse(wallet), coinAmount.getAmount());
    }

    public CoinTransactionResponse exchangeCoin(CoinExchangeRequest coinExchangeRequest) {
        Optional<CoinAmount> coinAmountOpt = coinAmountRepository.findById(coinExchangeRequest.getCoinAmountId());
        Optional<Coin> coinOpt = coinRepository.findById(coinExchangeRequest.getCoinId());
        if (coinOpt.isEmpty()) {
            throw new NotFoundException("Coin not found!");
        }
        if (coinAmountOpt.isEmpty()) {
            throw new NotFoundException("CoinAmount not found!");
        }

        Coin coin = coinOpt.get();
        CoinAmount existingCoinAmount = coinAmountOpt.get();

        double valueOfBoughtCoinsInUsd = coin.getValue() * coinExchangeRequest.getAmount();
        double valueOfHeldCoinAmountInUsd = existingCoinAmount.getCoin().getValue() * existingCoinAmount.getAmount();

        if (valueOfBoughtCoinsInUsd > valueOfHeldCoinAmountInUsd) {
            throw new NotEnoughFundsException("Not enough funds to buy the desired coin amount!");
        }

        CoinAmount desiredCoinAmount = getOrCreateCoinAmount(coin, existingCoinAmount.getWallet());

        existingCoinAmount.setAmount((valueOfHeldCoinAmountInUsd - valueOfBoughtCoinsInUsd)/existingCoinAmount.getCoin().getValue());
        desiredCoinAmount.setAmount(desiredCoinAmount.getAmount() + valueOfBoughtCoinsInUsd/desiredCoinAmount.getCoin().getValue());

        coinAmountRepository.save(existingCoinAmount);
        coinAmountRepository.save(desiredCoinAmount);

        return new CoinTransactionResponse(desiredCoinAmount);
    }

    private void checkIfEnoughFunds(CoinExchangeRequest coinExchangeRequest, Coin coin, CoinAmount coinAmount) throws NotEnoughFundsException {

    }

    private CoinAmount getOrCreateCoinAmount(Coin coin, Wallet wallet) {
        CoinAmount coinAmount = coinAmountRepository.findByWalletAndCoin(wallet, coin);
        if (coinAmount == null) {
            // create new coin amount if it doesn't exist
            coinAmount = new CoinAmount(wallet, coin, 0d);
        }
        return coinAmount;
    }

    public ListCoinTransactionResponse getAllCoinsFromWallet(Long walletId) throws NotFoundException {
        Optional<Wallet> walletOpt = walletRepository.findById(walletId);
        if (walletOpt.isEmpty()) {
            throw new NotFoundException("Wallet not found!");
        }
        List<CoinAmount> coinAmounts = coinAmountRepository.findAllByWallet(walletOpt.get());
        List<CoinTransactionResponse> coinTransactionResponses = new ArrayList<>();
        for (CoinAmount coinAmount :
                coinAmounts) {
            coinTransactionResponses.add(new CoinTransactionResponse(coinAmount));
        }
        return new ListCoinTransactionResponse(coinTransactionResponses);
    }

    /* Assignment 1 */
    public float getTotalUSDValueFromWallet(Long walletId) throws NotFoundException {
        Optional<Wallet> walletOpt = walletRepository.findById(walletId);
        float TotalValue = 0;
        if (walletOpt.isEmpty()) {
            throw new NotFoundException("Wallet not found!");
        } else {
            List<CoinAmount> coinAmounts = coinAmountRepository.findAllByWallet(walletOpt.get());
            for (CoinAmount coinAmount : coinAmounts) {
                TotalValue += coinAmount.getCoin().getValue() * coinAmount.getAmount();
            }
        }
        return TotalValue;
    }
}
