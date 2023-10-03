package com.app.infra.database;

import com.app.domain.Todo;
import com.app.domain.gateway.TodoSearch;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class TodoSearchAdapter implements TodoSearch {
    @Override
    public Mono<Todo> findByComment(Todo todo) {
        return null;
    }
}
