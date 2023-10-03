package com.app.infra.entrypoint.rest;

import com.app.application.AddTodo;
import com.app.infra.entrypoint.rest.domain.AddTodoReq;
import com.app.shared.domain.exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
@AllArgsConstructor
public class
TodoHandler {

    private final AddTodo addTodo;
    public Mono<ServerResponse> addTodo(ServerRequest request){

        return request.bodyToMono(AddTodoReq.class)
                .flatMap(req -> addTodo.save(req.getId(), req.getComment()))
                .map(Optional::of)
                .defaultIfEmpty(Optional.empty())
                .flatMap(voidOptional -> ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(Mono.just("Hey it is ok"), String.class))
                .onErrorResume(BusinessException.class, e -> ServerResponse.status(400)
                             .body(Mono.just(e.getMessage()), String.class));
    }
}
