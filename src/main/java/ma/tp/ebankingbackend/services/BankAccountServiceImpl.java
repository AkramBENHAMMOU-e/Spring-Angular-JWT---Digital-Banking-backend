package ma.tp.ebankingbackend.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.tp.ebankingbackend.entities.*;
import ma.tp.ebankingbackend.enums.AccountStatus;
import ma.tp.ebankingbackend.enums.OperationType;
import ma.tp.ebankingbackend.repositories.AccountOperationRepository;
import ma.tp.ebankingbackend.repositories.BankAccountRepository;
import ma.tp.ebankingbackend.repositories.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service @Transactional @AllArgsConstructor @Slf4j
public class BankAccountServiceImpl implements BankAccountService{
    BankAccountRepository bankAccountRepository;
    CustomerRepository customerRepository;
    AccountOperationRepository accountOperationRepository;

    @Override
    public Customer saveCustomer(Customer customer) {
        log.info("Saving customer " + customer);
        return customerRepository.save(customer);
    }

    @Override
    public CurrentAccount saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.setId(UUID.randomUUID().toString());
        currentAccount.setBalance(initialBalance);
        currentAccount.setCreatedAt(new Date());
        currentAccount.setOverDraft(overDraft);
        return bankAccountRepository.save(currentAccount);
    }

    @Override
    public SavingAccount saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        SavingAccount savingAccount= new SavingAccount();
        savingAccount.setId(UUID.randomUUID().toString());
        savingAccount.setBalance(initialBalance);
        savingAccount.setCreatedAt(new Date());
        savingAccount.setInterestRate(interestRate);
        return bankAccountRepository.save(savingAccount);
    }


    @Override
    public List<Customer> listCustomers() {

        return customerRepository.findAll();
    }

    @Override
    public BankAccount getBankAccount(String AccountId) {
        BankAccount bankAccount = bankAccountRepository.findById(AccountId).orElse(null);
        if (bankAccount == null) {
            throw new RuntimeException("Bank account not found");
        }
        log.info("Bank account found: " + bankAccount.getId());
        return bankAccount;
    }

    @Override
    public void debit(String accountId, double amount, String description) {
        BankAccount bankAccount = getBankAccount(accountId);
        if (bankAccount.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }
        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setOperationDate(new Date());
        accountOperation.setAmount(amount);
        accountOperation.setType(OperationType.Debit);
        accountOperation.setDescription(description);
        accountOperation.setBankAccount(bankAccount);
        accountOperationRepository.save(accountOperation);
        bankAccount.setBalance(bankAccount.getBalance() - amount);
        bankAccountRepository.save(bankAccount);
        log.info("Debit operation done: " + amount + " from account " + accountId);

    }

    @Override
    public void credit(String accountId, double amount, String description) {
        BankAccount bankAccount = getBankAccount(accountId);
        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setOperationDate(new Date());
        accountOperation.setAmount(amount);
        accountOperation.setType(OperationType.Credit);
        accountOperation.setDescription(description);
        accountOperation.setBankAccount(bankAccount);
        accountOperationRepository.save(accountOperation);
        bankAccount.setBalance(bankAccount.getBalance() + amount);
        bankAccountRepository.save(bankAccount);
        log.info("Debit operation done: " + amount + " from account " + accountId);
    }

    @Override
    public void transfer(String accountIdSource, String accountIdDestination, double amount) {
        debit(accountIdSource, amount, "Transfer to " + accountIdDestination);
        credit(accountIdDestination, amount, "Transfer from " + accountIdSource);
        log.info("Transfer operation done: " + amount + " from account " + accountIdSource + " to account " + accountIdDestination);

    }

    public List<BankAccount> listBankAccounts() {
        return bankAccountRepository.findAll();
    }
}
