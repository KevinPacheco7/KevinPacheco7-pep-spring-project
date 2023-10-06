package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.context.annotation.Configuration;

@Configuration
@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public List<Message> getMessageByAccId(int account_id){
        if(messageRepository.existsById(account_id)){
            return messageRepository.getAllMessagesByAccId(account_id);
        }
        List<Message> emptyList = new ArrayList<>();
        return emptyList;
    }

    public int updateMessageById(String message_txt, int message_id){
        if(message_txt.length()>=255 || message_txt.isBlank()){
            return 0;
        }
        Optional<Message> holder = messageRepository.findById(message_id);
        if(holder.isPresent()){
            Message m = holder.get();
            m.setMessage_text(message_txt);
            messageRepository.save(m);
            return 1;
        }
        return 0;
    }
    
    public int deleteAllMessagesById(int message_id){
        Optional<Message> holder = messageRepository.findById(message_id);
        if(holder.isPresent()){
            messageRepository.deleteById(message_id);
            return 1;
        }
        return 0;
    }
    
    public Message AllMessagesById(int id){
        return messageRepository.getAllMessagesById(id);
    }

    public List<Message> AllMessages(){
        return messageRepository.getAllMessages();
    }

    public Message addMessage(Message message) throws Exception{
        if(message.getMessage_text().length()>=255 || message.getMessage_text().isBlank() || 
        messageRepository.getMessagebyPostBy(message.getPosted_by())==null){
            throw new Exception();
        }
        return messageRepository.save(message);
    }
}
