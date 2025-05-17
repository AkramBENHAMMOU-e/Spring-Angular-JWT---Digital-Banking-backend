package ma.tp.ebankingbackend.web;

import lombok.AllArgsConstructor;
import ma.tp.ebankingbackend.dtos.*;
import ma.tp.ebankingbackend.services.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class BankAccountRestAPI {
    private BankAccountService bankAccountService;
     public BankAccountRestAPI(BankAccountService bankAccountService) {
         this.bankAccountService = bankAccountService;
     }

     @GetMapping("/accounts/{accountId}")
      public BankAccountDTO getBankAccount(@PathVariable String accountId) {
          return bankAccountService.getBankAccount(accountId);
      }


      @GetMapping("/accounts")
      public List<BankAccountDTO> listAccounts() {
          return bankAccountService.bankAccountList();
      }


    @GetMapping("/accounts/{accountId}/operations")
    public   List<AccountOperationDTO> getAccountHistory(@PathVariable  String accountId) {
        return bankAccountService.accountHistory(accountId);
    }
    @GetMapping("/accounts/{accountId}/pageOperations")
    public AccountHistoryDTO getHistory(@PathVariable  String accountId,
                                        @RequestParam(name = "page",defaultValue = "0") int page,
                                        @RequestParam(name = "size", defaultValue = "5") int size) {
        return bankAccountService.getAccountHistory(accountId,page,size);
    }

    @PostMapping("/accounts/debit")
    public DebitDTO debit(@RequestBody DebitDTO debitDTO) {
        this.bankAccountService.debit(debitDTO.getAccountId(),debitDTO.getAmount(),debitDTO.getDescription());
        return debitDTO;
    }
    @PostMapping("/accounts/credit")
    public CreditDTO credit(@RequestBody CreditDTO creditDTO) {
        this.bankAccountService.credit(creditDTO.getAccountId(),creditDTO.getAmount(),creditDTO.getDescription());
        return creditDTO;
    }
    @PostMapping("/accounts/transfer")
    public void transfer(@RequestBody TransferRequestDTO transferRequestDTO)  {
        this.bankAccountService.transfer(
                transferRequestDTO.getAccountSource(),
                transferRequestDTO.getAccountDestination(),
                transferRequestDTO.getAmount());
    }

}
