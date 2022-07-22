package com.dh.reactivespingjavatechie.handler;

import com.dh.reactivespingjavatechie.dao.CustomerDAO;
import com.dh.reactivespingjavatechie.dto.Customer;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public record CustomerStreamHandler(CustomerDAO dao) {
    public Mono<ServerResponse> getCustomers(ServerRequest request){
        Flux<Customer> customerStream = dao.getCustomersStream();
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(customerStream, Customer.class);
    }
}
