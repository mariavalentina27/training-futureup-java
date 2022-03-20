package ro.zynk.futureup.domain.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ro.zynk.futureup.domain.dtos.Coin;

@Repository
public interface CoinRepository extends PagingAndSortingRepository<Coin, Long> {
}
