package com.app.infra.entrypoint.rest;

import com.app.application.AddTodo;
import com.app.infra.entrypoint.rest.domain.AddTodoReq;
import com.app.infra.entrypoint.rest.domain.HttpPath;
import com.app.shared.domain.exception.BusinessException;
import com.app.shared.domain.exception.ConstantBusinessException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {TodoHandler.class, RouterTodo.class})
@WebFluxTest
class TodoHandlerTest {

    @MockBean
    AddTodo addTodo;

    @Autowired
    private WebTestClient webClient;

    @Test
    void addTodoOkTest() {
        var request = AddTodoReq.builder()
                .id(100L)
                .comment("test")
                .build();

        when(addTodo.save(anyLong(), anyString())).thenReturn(Mono.empty());

        webClient.post()
                .uri(HttpPath.ADD_TODO)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), AddTodoReq.class)
                .exchange()
                .expectStatus().isEqualTo(200)
                .expectBody(String.class)
                .consumeWith(response ->{
                    System.out.println("respuesta -> "+response.getResponseBody());
                });
    }


    @Test
    void addTodoExceptionTest() {
        var request = AddTodoReq.builder()
                .id(100L)
                .comment("test")
                .build();
        var businessException = new BusinessException(ConstantBusinessException.TASK_EXIST);
        when(addTodo.save(anyLong(), anyString())).thenReturn(Mono.error(businessException));

        webClient.post()
                .uri(HttpPath.ADD_TODO)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), AddTodoReq.class)
                .exchange()
                .expectStatus().isEqualTo(400)
                .expectBody(String.class) //Api Response
                .consumeWith(response ->{
                    System.out.println("respuesta -> "+response.getResponseBody());
                });
    }
}