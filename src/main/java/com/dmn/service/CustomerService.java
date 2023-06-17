package com.dmn.service;

import com.dmn.domain.Customer;
import com.dmn.dto.CustomerDTO;
import com.dmn.exception.AlreadyEmailFoundException;
import com.dmn.exception.CustomerNotFoundException;
import com.dmn.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public List<Customer> getAllCustomer() {

        return customerRepository.findAll();

    }

    public void saveCustomer(Customer customer) {
        Boolean a=customerRepository.existsByEmail(customer.getEmail());

        if(a){
            throw  new AlreadyEmailFoundException("This customer has already created!");
        }

        customerRepository.save(customer);
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(()->new CustomerNotFoundException("Customer not found by id : "+id));
    }

    public void deleteById(Long id) {
        Customer customer=getCustomerById(id);
        customerRepository.delete(customer);

    }

    public void updateCustomerById(Long id, CustomerDTO customerDTO) {


        Customer foundCustomer = getCustomerById(id);


        Boolean exists = customerRepository.existsByEmail(customerDTO.getEmail());

        if (exists && !foundCustomer.getEmail().equals(customerDTO.getEmail())) {
            throw new AlreadyEmailFoundException("Bu email zaten baska biri tarafindan kullaniliyor...");
        }

        foundCustomer.setName(customerDTO.getName());
        foundCustomer.setLastname(customerDTO.getLastname());
        foundCustomer.setGrade(customerDTO.getGrade());
        foundCustomer.setPhoneNumber(customerDTO.getPhoneNumber());
        foundCustomer.setEmail(customerDTO.getEmail());

        customerRepository.save(foundCustomer);


    }

    public Page<Customer> getAllCustmByPaging(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    public List<Customer> getCustomerBylasName(String lastname) {

        return customerRepository.findAllBylastname(lastname);


    }

    public List<Customer> getAllCustByGrade(Integer grade) {
        return customerRepository.findAllBygrade(grade);
    }

//    public void saveCustomer(Customer customer) {
//

//
//        customerRepository.save(customer);
//    }














}
