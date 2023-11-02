package com.example.irrigationuniversity.services;

import com.example.irrigationuniversity.entity.Message;
import com.example.irrigationuniversity.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    @Override
    public ResponseEntity<Message> saveMessage(Message message){
        message.setCreatedAt(LocalDateTime.now());
       return ResponseEntity.ok().body( messageRepository.save(message));
    }
    @Override
    public ResponseEntity<List<Message>> getAllMessages(){
        return ResponseEntity.ok().body(messageRepository.findAllByOrder());
    }
    @Override
    public ResponseEntity<String> deleteMessage(UUID id){
        messageRepository.deleteById(id);
        return ResponseEntity.ok().body("Successfully deleted!");
    }
    @Override
    public ResponseEntity<String> deleteAllMessages(){
         messageRepository.deleteAll();
         return ResponseEntity.ok().body("Successfully deleted!");
    }
}
