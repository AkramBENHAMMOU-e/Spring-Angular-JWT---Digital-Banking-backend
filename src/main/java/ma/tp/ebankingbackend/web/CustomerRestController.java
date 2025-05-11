package ma.tp.ebankingbackend.web;

import lombok.AllArgsConstructor;
import ma.tp.ebankingbackend.dtos.CustomerDTO;
import ma.tp.ebankingbackend.entities.Customer;
import ma.tp.ebankingbackend.services.BankAccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CustomerRestController {
    BankAccountService bankAccountService;
    @GetMapping("/customers")
    public List<CustomerDTO> customers() {
        return bankAccountService.listCustomers();
    }
}
