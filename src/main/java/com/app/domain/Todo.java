package com.app.domain;


import com.app.domain.value.TodoComment;
import com.app.domain.value.TodoStatus;

public record Todo(Long id, TodoComment comment  , TodoStatus status) {



}
