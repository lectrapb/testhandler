package com.app;

import com.app.shared.domain.UseCase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = UseCase.class)
)
public class TodoTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoTestApplication.class, args);
    }

}
