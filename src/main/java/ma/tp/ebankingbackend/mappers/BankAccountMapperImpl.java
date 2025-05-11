package ma.tp.ebankingbackend.mappers;

import ma.tp.ebankingbackend.dtos.CustomerDTO;
import ma.tp.ebankingbackend.entities.Customer;
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
}
