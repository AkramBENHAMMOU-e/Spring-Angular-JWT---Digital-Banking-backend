package ma.tp.ebankingbackend.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;


@Data @AllArgsConstructor @NoArgsConstructor
@Entity
//@DiscriminatorValue("SA")
public class SavingAccount extends BankAccount {
    private double interestRate;

}
