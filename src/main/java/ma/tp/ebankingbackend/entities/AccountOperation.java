package ma.tp.ebankingbackend.entities;

import jakarta.persistence.*;
import lombok.*;
import ma.tp.ebankingbackend.enums.OperationType;

import java.util.Date;
@Data @AllArgsConstructor @NoArgsConstructor @Entity
public class AccountOperation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date operationDate;
    private double amount;
    private OperationType type;
    @ManyToOne
    private BankAccount bankAccount;
}
