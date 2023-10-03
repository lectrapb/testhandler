package com.app.infra.database;

import com.app.domain.Todo;
import com.app.domain.gateway.TodoAddGateway;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class TodoAddAdapter implements TodoAddGateway {
    @Override
    public Mono<Void> save(Todo todo) {
        return null;
    }
}
