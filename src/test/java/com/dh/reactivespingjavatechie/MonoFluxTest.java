package com.dh.reactivespingjavatechie;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {
    @Test
    public void testMono(){
        Mono<?> monoString = Mono.just("abc")
                .then(Mono.error(new RuntimeException("A error occurred")))
                .log();
        monoString.subscribe(System.out::println, (e) -> System.out.println(e.getMessage()));
    }
    @Test
    public void testFlux(){
        Flux<String> fluxString = Flux.just("spring","spring boot","webflux", "microservices")
                .concatWithValues("Additional")
                .concatWith(Flux.error(new RuntimeException("A error occurred on Flux Error")))
                .concatWithValues("Not executable")
                .log();
        fluxString.subscribe(System.out::println, (e) -> System.out.println(e.getMessage()));
    }
}
