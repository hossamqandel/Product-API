package com.example.product_api.service;
import com.example.product_api.entity.Customer;
import com.example.product_api.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CustomerServiceImp implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Customer createCustomer(Customer customer) {
        Optional<Customer> existingCustomer = customerRepository.findByEmail(customer.getEmail().toLowerCase());
        if (existingCustomer.isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setEmail(customer.getEmail().toLowerCase());
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("this customer not exist"));
    }

    @Override
    public Optional<Customer> updateCustomer(Long id, Customer updatedCustomer) {
        return customerRepository.findById(id).map(existingCustomer -> {
            if (updatedCustomer.getName() != null) {
                existingCustomer.setName(updatedCustomer.getName());
            }
            if (updatedCustomer.getEmail() != null) {
                existingCustomer.setEmail(updatedCustomer.getEmail());
            }
            return customerRepository.save(existingCustomer);
        });
    }

    @Override
    public void changePassword(Long id, String newPassword) {
        customerRepository.findById(id).ifPresent(customer -> {
            customer.setPassword(passwordEncoder.encode(newPassword));
            customerRepository.save(customer);
        });
    }
}