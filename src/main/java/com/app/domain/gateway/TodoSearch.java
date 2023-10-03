package com.app.domain.gateway;

import com.app.domain.Todo;
import reactor.core.publisher.Mono;

public interface TodoSearch {

  Mono<Todo> findByComment(Todo todo);
}
