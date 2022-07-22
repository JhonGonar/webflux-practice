package com.dh.reactivespingjavatechie.router;

import com.dh.reactivespingjavatechie.handler.CustomerHandler;
import com.dh.reactivespingjavatechie.handler.CustomerStreamHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig{
    private final CustomerHandler handler;
    private final CustomerStreamHandler stream;
    public RouterConfig(CustomerHandler handler, CustomerStreamHandler stream) {
        this.handler = handler;
        this.stream = stream;
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction(){
        return RouterFunctions.route()
                .GET("/router/customers", handler::loadCustomers)
                .GET("/router/customers/stream", stream::getCustomers)
                .GET("/router/customers/{input}", handler::findCustomer)
                .POST("/router/customers/save", handler::saveCustomer)
                .build();
    }
}
