package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.Message;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{

    @Query(value = "SELECT * FROM message WHERE posted_by = :account_id ",nativeQuery = true)
    List<Message> getAllMessagesByAccId(@Param("account_id") int account_id);
    
    @Query(value = "SELECT * FROM message WHERE message_id = :message_id ",nativeQuery = true)
    Message getAllMessagesById(@Param("message_id") int message_id);

    @Query(value = "SELECT * FROM message",nativeQuery = true)
    List<Message> getAllMessages();

    @Query(value = "SELECT * FROM message WHERE posted_by = :posted_by",nativeQuery = true)
    Message getMessagebyPostBy(@Param("posted_by") int posted_by);
}
