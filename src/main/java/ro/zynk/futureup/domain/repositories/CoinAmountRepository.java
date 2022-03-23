package ro.zynk.futureup.domain.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ro.zynk.futureup.domain.dtos.CoinAmount;

@Repository
public interface CoinAmountRepository extends PagingAndSortingRepository<CoinAmount, Long> {
}
