package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.exception.accountException;
import com.example.repository.AccountRepository;

import org.springframework.context.annotation.Configuration;

@Configuration
@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account addAccount1(Account account)throws accountException{
        Account holder = getAccount(account.getUsername());
        if(account.getPassword().length()<4 || account.getUsername().equals("")){
            return null;
        }
        if(holder==null){
            Account acc =accountRepository.save(account);
            return acc;
        }
        throw new accountException();
    }

    public Account getAccount(String user){
        System.out.println(accountRepository.findAccountByUser(user));
        return accountRepository.findAccountByUser(user);
    }
}
