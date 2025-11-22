package com;

import com.Persistence.Persister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class
})
public class Main {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((t,e)-> System.err.println(e));
        Persister.startDBConnection();
        SpringApplication.run(Main.class, args);
    }
}