package ro.zynk.futureup.services;

import org.springframework.stereotype.Service;
import ro.zynk.futureup.controllers.requests.CoinTransactionRequest;
import ro.zynk.futureup.domain.dtos.Coin;
import ro.zynk.futureup.domain.dtos.Transaction;
import ro.zynk.futureup.domain.repositories.TransactionRepository;
import ro.zynk.futureup.exceptions.NotFoundException;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    private Coin coin;

    private CoinTransactionRequest coinAmount;

    public TransactionService (TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public float getTotalTransactionValue (CoinTransactionRequest coinAmount, Coin coin) {
        float value = (float) (coinAmount.getAmount() * coin.getValue());
        return value;
    }

    /* Assignment 2 */
    public List<Transaction> getallTransactions() throws NotFoundException {
        List<Transaction> transactions = transactionRepository.findAll();
        if(transactions.isEmpty())
            throw new NotFoundException("There is no transaction to be found!");
        return transactions;
    }

    /* Assignment 2 */
    public List<Transaction> getTransactions(float amountOfUSD) throws NotFoundException {
        List<Transaction> transactions = transactionRepository.findBytotalValueGreaterThan(amountOfUSD);
        if(transactions.isEmpty())
            throw new NotFoundException("There is no transaction with a total price greater than the amount of USD given!");
        return transactions;
    }
}
