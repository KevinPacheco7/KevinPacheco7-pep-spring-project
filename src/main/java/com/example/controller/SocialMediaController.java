package com.example.controller;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;

import com.example.exception.accountException;

import java.util.List;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    @Autowired
    AccountService accountService;

    @Autowired
    MessageService messageService;
    
    @GetMapping("/accounts/{account_id}/messages")
    public ResponseEntity<List<Message>> getMessageByAccountId(@PathVariable int account_id){
        List<Message> mess= messageService.getMessageByAccId(account_id);
        return new ResponseEntity<List<Message>>(mess,HttpStatus.OK);
    }

    @PatchMapping(value = "/messages/{message_id}")
    public ResponseEntity<Integer> patchMessage(@RequestBody Message message, @PathVariable int message_id){
        if(messageService.updateMessageById(message.getMessage_text(),message_id)==1){
            return new ResponseEntity<Integer>(1,HttpStatus.OK);
        }else{
            return ResponseEntity.status(400).body(0);
        }
    }

    @DeleteMapping(value = "/messages/{message_id}")
    public ResponseEntity<Integer> deleteMessagebyId(@PathVariable int message_id){
        if(messageService.deleteAllMessagesById(message_id)==1){
            return new ResponseEntity<Integer>(1,HttpStatus.OK);
        }else{
            return new ResponseEntity<Integer>(0,HttpStatus.OK);
        }
    }

    @GetMapping(value = "/messages/{message_id}")
    public ResponseEntity<Message> getMessagebyId(@PathVariable int message_id){
        Message mess= messageService.AllMessagesById(message_id);
        return new ResponseEntity<Message>(mess,HttpStatus.OK);
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages(){
        List<Message> mess= messageService.AllMessages();
        return new ResponseEntity<List<Message>>(mess,HttpStatus.OK);
    }

    @PostMapping(value ="/messages")
    public ResponseEntity<Message> postMessage(@RequestBody Message message){
        try{
            Message m = messageService.addMessage(message);
            return new ResponseEntity<Message>(m,HttpStatus.OK);
        } catch(Exception e){}
        return ResponseEntity.status(400).body(null);
    }

    @PostMapping(value ="/register")
    public ResponseEntity<Account> postAccount(@RequestBody Account account){
        try {
            Account acc =accountService.addAccount1(account);
            return new ResponseEntity<Account>(acc,HttpStatus.OK);
        }catch(accountException e){
            return ResponseEntity.status(409).body(null);
        } catch (Exception e){
            return ResponseEntity.status(400).body(null);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Account> loginn(@RequestBody Account account){
        if(accountService.getAccount(account.getUsername())==null){
            return ResponseEntity.status(401).body(null);
        }
        String username=accountService.getAccount(account.getUsername()).getUsername();
        String password=accountService.getAccount(account.getUsername()).getPassword();
        if(username.equals(account.getUsername())){
            if(password.equals(account.getPassword())){ 
                Account acc = accountService.getAccount(username);
                return ResponseEntity.status(200).body(acc);
            }
        }
        return ResponseEntity.status(401).body(null);
    }
}
