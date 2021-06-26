package com.cphong.springmongo;

import com.cphong.springmongo.account.Account;
import com.cphong.springmongo.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class SpringmongoApplication {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    AccountRepository accountRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringmongoApplication.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
                Account account = new Account();

                account.setEmail( "cphong@email.com");
                account.setUsername("cphong");

//                mongoTemplate.insert(account);
                accountRepository.insert(account);

            System.out.println("finished");

        };
    }
}
