package ma.tp.ebankingbackend.services;

import ma.tp.ebankingbackend.entities.BankAccount;
import ma.tp.ebankingbackend.entities.CurrentAccount;
import ma.tp.ebankingbackend.entities.Customer;
import ma.tp.ebankingbackend.entities.SavingAccount;

import java.util.List;

public interface BankAccountService {

    Customer saveCustomer(Customer customer);
    CurrentAccount saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId);
    SavingAccount saveSavingBankAccount(double initialBalance, double interestRate, Long customerId);
    List<Customer> listCustomers();
    BankAccount getBankAccount(String AccountId);
    void debit(String accountId, double amount, String description);
    void credit(String accountId, double amount, String description);
    void transfer(String accountIdSource, String accountIdDestination, double amount);
   List<BankAccount> listBankAccounts();

}
