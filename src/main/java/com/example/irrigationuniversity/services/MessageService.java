package com.example.irrigationuniversity.services;

import com.example.irrigationuniversity.entity.Message;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface MessageService {
    ResponseEntity<List<Message>> getAllMessages();

    ResponseEntity<Message> saveMessage(Message message);

    ResponseEntity<String> deleteMessage(UUID id);

    ResponseEntity<String> deleteAllMessages();
}
