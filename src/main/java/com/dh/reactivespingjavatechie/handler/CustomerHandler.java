package com.dh.reactivespingjavatechie.handler;

import com.dh.reactivespingjavatechie.dao.CustomerDAO;
import com.dh.reactivespingjavatechie.dto.Customer;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public record CustomerHandler(CustomerDAO dao) {
    public Mono<ServerResponse> loadCustomers(ServerRequest request){
        Flux<Customer> customersList = dao.getCustomersList();
        return ServerResponse.ok().body(customersList, Customer.class);
    }

    public Mono<ServerResponse> findCustomer(ServerRequest request){
        int customerId = Integer.parseInt(request.pathVariable("input"));
        //dao.getCustomersList().filter(c -> c.id() == customerId).single();
        Mono<Customer> mono = dao.getCustomersList().filter(c -> c.id() == customerId).next();
        return ServerResponse.ok().body(mono, Customer.class);
    }

    public Mono<ServerResponse> saveCustomer(ServerRequest request) {
        Mono<Customer> customerMono = request.bodyToMono(Customer.class);
        Mono<String> saveResponse = customerMono.map(dto -> dto.id()+": "+ dto.name());
        return ServerResponse.ok().body(saveResponse, String.class);
    }
}
