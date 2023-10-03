package com.app.application;

import com.app.domain.Todo;
import com.app.domain.gateway.TodoAddGateway;
import com.app.domain.gateway.TodoSearch;
import com.app.domain.value.TodoComment;
import com.app.domain.value.TodoStatus;
import com.app.shared.domain.exception.ConstantBusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AddTodoTest {

    private  TodoSearch todoSearch;
    private  TodoAddGateway todoAdd;
    private AddTodo useCase;

    @BeforeEach
    void setUp(){
        todoSearch = mock(TodoSearch.class);
        todoAdd = mock(TodoAddGateway.class);
        useCase = new AddTodo(todoSearch, todoAdd);
    }
    @Test
    void save_null_test() {
        //given
        //when
        //then
        useCase.save(null, null)
                .as(StepVerifier::create)
                .expectErrorMessage(ConstantBusinessException.DATA_REQUIRED)
                .verify();
    }


    @Test
    void save_data_exist_test() {
        //given
        var todo = new Todo(1L, new TodoComment("test"), new TodoStatus(Boolean.TRUE));
        //when
        when(todoSearch.findByComment(any())).thenReturn(Mono.just(todo));
        when(todoAdd.save(any())).thenReturn(Mono.empty());
        //then
        useCase.save(1L , "test")
                .as(StepVerifier::create)
                .expectErrorMessage(ConstantBusinessException.TASK_EXIST)
                .verify();
    }

    @Test
    void save() {
        //given
        var todo = new Todo(1L, new TodoComment("test"), new TodoStatus(Boolean.TRUE));
        //when
        when(todoSearch.findByComment(any())).thenReturn(Mono.empty());
        when(todoAdd.save(any())).thenReturn(Mono.empty());
        //then
        useCase.save(1L , "test")
                .as(StepVerifier::create)
                .expectNextCount(0)
                .verifyComplete();
    }
}