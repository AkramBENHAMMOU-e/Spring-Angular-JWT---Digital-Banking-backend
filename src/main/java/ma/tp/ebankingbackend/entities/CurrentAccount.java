package ma.tp.ebankingbackend.entities;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@DiscriminatorValue("CA")
public class CurrentAccount extends BankAccount {
    private double overDraft;
}
