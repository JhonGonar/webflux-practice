package com.dh.reactivespingjavatechie.controller;

import com.dh.reactivespingjavatechie.dto.Customer;
import com.dh.reactivespingjavatechie.service.CustomerService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/customers")
public record CustomerController(CustomerService service) {
    @GetMapping
    public List<Customer> getAllCustomers() throws InterruptedException {
        return service.loadAllCustomers();
    }
    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Customer> getAllCustomersStream(){
        return service.loadAllCustomersStream();
    }
}
