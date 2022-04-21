package com.almox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;

@SpringBootApplication
public class AlmoxApplication {

    public static void main(String[] args) {
        Locale.setDefault(new Locale("pt", "BR"));
        SpringApplication.run(AlmoxApplication.class, args);
    }

}
