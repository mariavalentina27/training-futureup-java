package ro.zynk.futureup.domain.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ro.zynk.futureup.domain.dtos.Wallet;

@Repository
public interface WalletRepository extends PagingAndSortingRepository<Wallet, Long> {
    Wallet getById(Long id);
}
