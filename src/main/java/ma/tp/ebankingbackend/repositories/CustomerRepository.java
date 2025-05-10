package ma.tp.ebankingbackend.repositories;

import ma.tp.ebankingbackend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);
    Customer findByIdAndEmail(Long id, String email);
    Customer findByIdAndEmailAndPassword(Long id, String email, String password);
}
