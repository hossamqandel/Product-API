package com.example.product_api.service;

import com.example.product_api.entity.Customer;

import java.util.Optional;

public interface CustomerService {
           Customer createCustomer(Customer customer);
           Customer getCustomerById(Long id);
           Optional<Customer> updateCustomer(Long id, Customer updatedCustomer);

           void changePassword(Long id, String newPassword);
     }


