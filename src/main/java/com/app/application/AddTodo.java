package com.app.application;

import com.app.domain.Todo;
import com.app.domain.gateway.TodoAddGateway;
import com.app.domain.gateway.TodoSearch;
import com.app.domain.value.TodoComment;
import com.app.domain.value.TodoStatus;
import com.app.shared.domain.UseCase;
import com.app.shared.domain.exception.BusinessException;
import com.app.shared.domain.exception.ConstantBusinessException;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Optional;

@UseCase
@AllArgsConstructor
public class AddTodo {

    private final  TodoSearch todoSearch;
    private final TodoAddGateway todoAdd;
    public Mono<Void>  save(Long id, String comment){

        return Mono.fromCallable(() -> getTodo(id, comment))
                .flatMap(todoSearch::findByComment)
                .map(Optional::of)
                .defaultIfEmpty(Optional.empty())
                .map(todo -> {
                    checkIfExist(todo.isPresent());
                    return getTodo(id, comment);
                })
                .flatMap(todoAdd::save);

    }

    private void checkIfExist(boolean isPresent) {
        if(isPresent){
            throw new BusinessException(ConstantBusinessException.TASK_EXIST);
        }
    }

    private  Todo getTodo(Long id, String comment) {
        return new Todo(id, new TodoComment(comment), new TodoStatus(Boolean.TRUE));
    }
}
