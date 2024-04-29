package com.ex.socket1;

import com.ex.socket1.service.SampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Socket1Application {

    public static void main(String[] args) {
        SpringApplication.run(Socket1Application.class, args);
        SampleService sampleService = new SampleService();
        sampleService.method();
    }

}
