package com.cphong.springredis;

import com.cphong.springredis.account.Account;
import com.cphong.springredis.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RedisRunner implements ApplicationRunner {


    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    AccountRepository accountRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {


        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set("name","cphong");
        values.set("springboot","2.5");
        values.set("hello","world");


        System.out.println(values.get("name"));
        System.out.println(values.get("springboot"));
        System.out.println(values.get("hello"));


        Account account = new Account();
        account.setEmail("cphong@email.com");
        account.setUsername("cphong");

        accountRepository.save(account);
        Optional<Account> byId = accountRepository.findById(account.getId());
        System.out.println( byId.get().getUsername());
        System.out.println(byId.get().getEmail());

    }



}
