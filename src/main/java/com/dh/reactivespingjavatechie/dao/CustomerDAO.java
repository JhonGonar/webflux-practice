package com.dh.reactivespingjavatechie.dao;

import com.dh.reactivespingjavatechie.dto.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDAO {

    private static void sleepExecution(int i){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Customer> getCustomers(){
        return IntStream.rangeClosed(1,50)
                .peek(CustomerDAO::sleepExecution)
                .peek(i -> System.out.println("iteration #: "+ i))
                .mapToObj(i -> new Customer(i, "customer "+i))
                .collect(Collectors.toList());
    }
    public Flux<Customer> getCustomersStream(){
        return Flux.range(1, 50)
                .delayElements(Duration.ofMillis(800))
                .doOnNext(i -> System.out.println("iteration stream #: "+ i))
                .map(i -> new Customer(i, "customer "+i));
    }

    public Flux<Customer> getCustomersList(){
        return Flux.range(1, 50)
                .doOnNext(i -> System.out.println("iteration stream #: "+ i))
                .map(i -> new Customer(i, "customer "+i));
    }
}
