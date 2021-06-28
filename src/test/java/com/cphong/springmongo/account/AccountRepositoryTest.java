package com.cphong.springmongo.account;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataMongoTest
class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void findByEmail() throws Exception {

        Account account = new Account();

        account.setUsername("cphong");
        account.setEmail("cphong@gmail.com");

        accountRepository.save(account);

        Optional<Account> byId = accountRepository.findById(account.getId());
        assertThat(byId).isNotEmpty();


        Optional<Account> byEmail = accountRepository.findByEmail(account.getEmail());
        assertThat(byEmail).isNotEmpty();
        assertThat(byEmail.get().getUsername()).isEqualTo("cphong");

    }

}