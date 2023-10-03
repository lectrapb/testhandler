package com.app.domain.gateway;

import com.app.domain.Todo;
import reactor.core.publisher.Mono;

public interface TodoAddGateway {

  Mono<Void> save(Todo todo);
}
