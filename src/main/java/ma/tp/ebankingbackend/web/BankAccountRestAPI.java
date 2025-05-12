package ma.tp.ebankingbackend.web;

import lombok.AllArgsConstructor;
import ma.tp.ebankingbackend.services.BankAccountService;
import org.springframework.web.bind.annotation.RestController;

@RestController @AllArgsConstructor
public class BankAccountRestAPI {

    private BankAccountService bankAccountService;
}
