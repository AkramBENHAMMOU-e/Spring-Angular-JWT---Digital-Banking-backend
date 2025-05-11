package ma.tp.ebankingbackend;

import ma.tp.ebankingbackend.entities.*;
import ma.tp.ebankingbackend.enums.AccountStatus;
import ma.tp.ebankingbackend.enums.OperationType;
import ma.tp.ebankingbackend.repositories.AccountOperationRepository;
import ma.tp.ebankingbackend.repositories.BankAccountRepository;
import ma.tp.ebankingbackend.repositories.CustomerRepository;
import ma.tp.ebankingbackend.services.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankingBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BankAccountService bankAccountService){
        return args -> {
           Stream.of("ahmad", "sara", "yassine").forEach(name -> {
                Customer customer = new Customer();
                customer.setName(name);
                customer.setEmail(name + "@gmail.com");
                bankAccountService.saveCustomer(customer);
            });
            bankAccountService.listCustomers().forEach(cust->{
                try {
                    bankAccountService.saveCurrentBankAccount(Math.random() * 90000, 9000, cust.getId());
                    bankAccountService.saveSavingBankAccount(Math.random() * 90000, 5.5, cust.getId());
                    bankAccountService.listBankAccounts().forEach(bankAccount->{
                        for (int i = 0; i < 5; i++) {
                           bankAccountService.credit(bankAccount.getId(), 100 + Math.random() * 9000, "credit");
                            bankAccountService.debit(bankAccount.getId(), 100 + Math.random() * 9000, "debit");
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        };
    }

    //@Bean
    CommandLineRunner start(CustomerRepository customerRepository, BankAccountRepository bankAccountRepository , AccountOperationRepository accountOperationRepository) {

        return args -> {
            Stream.of("ahmad", "sara", "yassine").forEach(name -> {
                Customer customer = new Customer();
                customer.setName(name);
                customer.setEmail(name + "@gmail.com");
                customerRepository.save(customer);
            });
            customerRepository.findAll().forEach(cust->{
                CurrentAccount currentAccount = new CurrentAccount();
                currentAccount.setId(UUID.randomUUID().toString());
                currentAccount.setBalance(Math.random() * 90000);
                currentAccount.setCreatedAt(new Date());
                currentAccount.setStatus(AccountStatus.CREATED);
                currentAccount.setCustomer(cust);
                currentAccount.setOverDraft(9000);
                bankAccountRepository.save(currentAccount);

                SavingAccount savingAccount = new SavingAccount();
                savingAccount.setId(UUID.randomUUID().toString());
                savingAccount.setBalance(Math.random() * 90000);
                savingAccount.setCreatedAt(new Date());
                savingAccount.setStatus(AccountStatus.CREATED);
                savingAccount.setCustomer(cust);
                savingAccount.setInterestRate(5);
                bankAccountRepository.save(savingAccount);
            });
            bankAccountRepository.findAll().forEach(acc->{
                for (int i = 0; i < 5; i++) {
                    AccountOperation accountOperation = new AccountOperation();
                    accountOperation.setOperationDate(new Date());
                    accountOperation.setAmount(Math.random() * 12000);
                    accountOperation.setType(Math.random() > 0.5 ? OperationType.Debit : OperationType.Credit);
                    accountOperation.setBankAccount(acc);
                    accountOperationRepository.save(accountOperation);

                }
            });

        };
    }
}
