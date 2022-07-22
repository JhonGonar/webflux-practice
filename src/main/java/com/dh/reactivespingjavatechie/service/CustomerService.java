package com.dh.reactivespingjavatechie.service;

import com.dh.reactivespingjavatechie.dao.CustomerDAO;
import com.dh.reactivespingjavatechie.dto.Customer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public record CustomerService(CustomerDAO dao) {
    public List<Customer> loadAllCustomers() throws InterruptedException {
        long start = System.currentTimeMillis();
        List<Customer> customers = dao.getCustomers();
        long end = System.currentTimeMillis();
        System.out.println("Total execution time: "+ (end-start));
        return customers;
    }

    public Flux<Customer> loadAllCustomersStream() {
        long start = System.currentTimeMillis();
        Flux<Customer> customers = dao.getCustomersStream();
        long end = System.currentTimeMillis();
        System.out.println("Total execution time: "+ (end-start));
        return customers;
    }
}
