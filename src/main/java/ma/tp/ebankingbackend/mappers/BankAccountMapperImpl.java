package ma.tp.ebankingbackend.mappers;

import ma.tp.ebankingbackend.dtos.CurrentBankAccountDTO;
import ma.tp.ebankingbackend.dtos.CustomerDTO;
import ma.tp.ebankingbackend.dtos.SavingBankAccountDTO;
import ma.tp.ebankingbackend.entities.CurrentAccount;
import ma.tp.ebankingbackend.entities.Customer;
import ma.tp.ebankingbackend.entities.SavingAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BankAccountMapperImpl {
    public CustomerDTO fromCustomer(Customer customer) {

        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
//        customerDTO.setId(customer.getId());
//        customerDTO.setName(customer.getName());
//        customerDTO.setEmail(customer.getEmail());

        return customerDTO;
    }

    public Customer fromCustomerDTO(CustomerDTO customerDTO) {

        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
//        customer.setId(customerDTO.getId());
//        customer.setName(customerDTO.getName());
//        customer.setEmail(customerDTO.getEmail());

        return customer;
    }

    public SavingBankAccountDTO fromSavingBankAccount(SavingAccount savingAccount) {
        SavingBankAccountDTO savingBankAccountDTO = new SavingBankAccountDTO();
        BeanUtils.copyProperties(savingAccount, savingBankAccountDTO);
        savingBankAccountDTO.setCustomerDTO(fromCustomer(savingAccount.getCustomer()));
        return savingBankAccountDTO;
    }

    public SavingAccount fromSavingBankAccountDTO(SavingBankAccountDTO savingBankAccountDTO) {
        SavingAccount savingAccount = new SavingAccount();
        BeanUtils.copyProperties(savingBankAccountDTO, savingAccount);
        savingAccount.setCustomer(fromCustomerDTO(savingBankAccountDTO.getCustomerDTO()));
        return savingAccount;
    }

    public CurrentBankAccountDTO fromCurrentBankAccount(CurrentAccount currentAccount) {
        CurrentBankAccountDTO currentBankAccountDTO = new CurrentBankAccountDTO();
        BeanUtils.copyProperties(currentAccount, currentBankAccountDTO);
        currentBankAccountDTO.setCustomerDTO(fromCustomer(currentAccount.getCustomer()));
        return currentBankAccountDTO;
    }
    public CurrentAccount fromCurrentBankAccountDTO(CurrentBankAccountDTO currentBankAccountDTO) {
        CurrentAccount currentAccount = new CurrentAccount();
        BeanUtils.copyProperties(currentBankAccountDTO, currentAccount);
        currentAccount.setCustomer(fromCustomerDTO(currentBankAccountDTO.getCustomerDTO()));
        return currentAccount;
    }
}
