package com.example.hellospringbatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // 이 어노테이션이 있어야 날짜 자동 저장 가능
@SpringBootApplication
public class HelloSpringBatchApplication {

    public static void main(String[] args) {
        SpringApplication.
                run(HelloSpringBatchApplication.class, args);
    }

}
