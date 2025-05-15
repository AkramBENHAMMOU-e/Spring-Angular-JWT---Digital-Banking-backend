package ma.tp.ebankingbackend.dtos;

import jakarta.persistence.*;
import lombok.Data;
import ma.tp.ebankingbackend.entities.BankAccount;
import ma.tp.ebankingbackend.enums.OperationType;

import java.util.Date;
@Data
public class AccountOperationDTO {
    private Long id;
    private Date operationDate;
    private double amount;
    private OperationType type;
    private String description;
}
