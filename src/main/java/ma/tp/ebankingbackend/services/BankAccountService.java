package ma.tp.ebankingbackend.services;

import ma.tp.ebankingbackend.dtos.*;
import ma.tp.ebankingbackend.entities.BankAccount;
import ma.tp.ebankingbackend.entities.CurrentAccount;
import ma.tp.ebankingbackend.entities.Customer;
import ma.tp.ebankingbackend.entities.SavingAccount;

import java.util.List;

public interface BankAccountService {



    CustomerDTO saveCustomer(CustomerDTO customerDTO);

    CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId);
    SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId);
    List<CustomerDTO> listCustomers();
    BankAccountDTO getBankAccount(String AccountId);
    void debit(String accountId, double amount, String description);
    void credit(String accountId, double amount, String description);
    void transfer(String accountIdSource, String accountIdDestination, double amount);
   List<BankAccountDTO> bankAccountList();

    CustomerDTO getCustomer(Long customerId);

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long customerId);

    List<AccountOperationDTO> accountHistory(String accountId);

    AccountHistoryDTO getAccountHistory(String accountId, int page, int size);

    List<CustomerDTO> searchCustomers(String keyword);
}
