package com.app.infra.entrypoint.rest;


import com.app.infra.entrypoint.rest.domain.HttpPath;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@AllArgsConstructor
public class RouterTodo {

    private final  TodoHandler todoHandler;

    @Bean
    public RouterFunction<ServerResponse> routes(){

        return route(POST(HttpPath.ADD_TODO), todoHandler::addTodo);
    }
}
