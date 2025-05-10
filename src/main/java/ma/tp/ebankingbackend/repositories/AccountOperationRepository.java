package ma.tp.ebankingbackend.repositories;

import ma.tp.ebankingbackend.entities.AccountOperation;
import ma.tp.ebankingbackend.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountOperationRepository extends JpaRepository<AccountOperation, Long> {
}
