package ma.tp.ebankingbackend.repositories;

import ma.tp.ebankingbackend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c WHERE c.name LIKE :kw")
    List<Customer> searchCustomer(@Param("kw") String keyword);
}
