package ro.zynk.futureup.domain.repositories;

import ch.qos.logback.core.pattern.parser.OptionTokenizer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ro.zynk.futureup.controllers.requests.CoinTransactionRequest;
import ro.zynk.futureup.domain.dtos.Coin;
import ro.zynk.futureup.domain.dtos.Transaction;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long> {
    public float getTotalTransactionValue (CoinTransactionRequest coinAmount, Coin coin);

    public List<Transaction> findAll();

    public List<Transaction> findBytotalValueGreaterThan(float totalValue);
}
