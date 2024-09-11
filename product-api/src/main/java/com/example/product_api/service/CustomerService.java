package com.example.product_api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.product_api.entity.Customer;
import com.example.product_api.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
//    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Customer createCustomer(Customer customer) {
        Optional<Customer> existingCustomer = customerRepository.findByEmail(customer.getEmail().toLowerCase());
        if (existingCustomer.isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        customer.setPassword(customer.getPassword());
        customer.setEmail(customer.getEmail().toLowerCase());
        return customerRepository.save(customer);
    }

    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("This customer not exist"));
    }

    public Customer updateCustomer(Long customerId, Customer updatedCustomer) {
        //Get Customer From DB + Ensure that he exist
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("This customer not exist"));

        System.out.println(updatedCustomer);

        //Check First if the Customer or Object properties not equal null
        Optional.ofNullable(updatedCustomer.getName()).ifPresent(name -> customer.setName(name));
        Optional.ofNullable(updatedCustomer.getEmail()).ifPresent(email -> customer.setEmail(email));
        //Save the already exist Customer again but with the new updates
        customerRepository.save(customer);
        return customer;
    }

    public void changePassword(Long customerId, String oldPassword, String newPassword) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("This customer not exist"));
        //Check if the oldPassword equals the already exist password
        if (!customer.getPassword().equals(oldPassword)){
            throw new IllegalArgumentException("Wrong password");
        }
        customer.setPassword(newPassword);
        customerRepository.save(customer);
    }
}