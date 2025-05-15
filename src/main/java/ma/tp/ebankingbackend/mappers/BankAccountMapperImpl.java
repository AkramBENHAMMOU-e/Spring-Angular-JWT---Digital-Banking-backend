package ma.tp.ebankingbackend.mappers;

import ma.tp.ebankingbackend.dtos.AccountOperationDTO;
import ma.tp.ebankingbackend.dtos.CurrentBankAccountDTO;
import ma.tp.ebankingbackend.dtos.CustomerDTO;
import ma.tp.ebankingbackend.dtos.SavingBankAccountDTO;
import ma.tp.ebankingbackend.entities.AccountOperation;
import ma.tp.ebankingbackend.entities.CurrentAccount;
import ma.tp.ebankingbackend.entities.Customer;
import ma.tp.ebankingbackend.entities.SavingAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BankAccountMapperImpl {

    public CustomerDTO fromCustomer(Customer customer) {
        if (customer == null) {
            return null; // Ne pas tenter de mapper si customer est null
        }
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }

    public Customer fromCustomerDTO(CustomerDTO customerDTO) {
        if (customerDTO == null) {
            return null; // Ne pas mapper si l'objet source est null
        }
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

    public SavingBankAccountDTO fromSavingBankAccount(SavingAccount savingAccount) {
        if (savingAccount == null) {
            return null; // Ne pas mapper si l'objet source est null
        }
        SavingBankAccountDTO savingBankAccountDTO = new SavingBankAccountDTO();
        BeanUtils.copyProperties(savingAccount, savingBankAccountDTO);
        // Protéger contre les clients null
        savingBankAccountDTO.setCustomerDTO(fromCustomer(savingAccount.getCustomer()));
        savingBankAccountDTO.setType(savingAccount.getClass().getSimpleName());
        return savingBankAccountDTO;
    }

    public SavingAccount fromSavingBankAccountDTO(SavingBankAccountDTO savingBankAccountDTO) {
        if (savingBankAccountDTO == null) {
            return null; // Ne pas mapper si l'objet source est null
        }
        SavingAccount savingAccount = new SavingAccount();
        BeanUtils.copyProperties(savingBankAccountDTO, savingAccount);
        // Protéger contre les clients null
        savingAccount.setCustomer(fromCustomerDTO(savingBankAccountDTO.getCustomerDTO()));
        return savingAccount;
    }

    public CurrentBankAccountDTO fromCurrentBankAccount(CurrentAccount currentAccount) {
        if (currentAccount == null) {
            return null; // Ne pas mapper si l'objet source est null
        }
        CurrentBankAccountDTO currentBankAccountDTO = new CurrentBankAccountDTO();
        BeanUtils.copyProperties(currentAccount, currentBankAccountDTO);
        // Protéger contre les clients null
        currentBankAccountDTO.setCustomerDTO(fromCustomer(currentAccount.getCustomer()));
        currentBankAccountDTO.setType(currentAccount.getClass().getSimpleName());
        return currentBankAccountDTO;
    }

    public CurrentAccount fromCurrentBankAccountDTO(CurrentBankAccountDTO currentBankAccountDTO) {
        if (currentBankAccountDTO == null) {
            return null; // Ne pas mapper si l'objet source est null
        }
        CurrentAccount currentAccount = new CurrentAccount();
        BeanUtils.copyProperties(currentBankAccountDTO, currentAccount);
        // Protéger contre les clients null
        currentAccount.setCustomer(fromCustomerDTO(currentBankAccountDTO.getCustomerDTO()));
        return currentAccount;
    }

    public AccountOperationDTO fromAccountOperation(AccountOperation accountOperation) {
        if (accountOperation == null) {
            return null; // Ne pas mapper si l'objet source est null
        }
        AccountOperationDTO accountOperationDTO = new AccountOperationDTO();
        BeanUtils.copyProperties(accountOperation, accountOperationDTO);
        return accountOperationDTO;
    }
}