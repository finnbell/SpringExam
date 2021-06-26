package com.cphong.springbootsecurity2;

import com.cphong.springbootsecurity2.account.Account;
import com.cphong.springbootsecurity2.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AccountRunner  implements ApplicationRunner {

    @Autowired
    AccountService accountService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account cphong = accountService.createAccount("cphong","1234");
        System.out.println( cphong.getUsername() +  "  password: " + cphong.getPassword() );
    }
}
