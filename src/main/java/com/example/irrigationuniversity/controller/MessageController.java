package com.example.irrigationuniversity.controller;

import com.example.irrigationuniversity.entity.Message;
import com.example.irrigationuniversity.services.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@CrossOrigin
@RestController
@RequestMapping("/api/message")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<Message> saveMessage(@RequestBody Message message){
    return  messageService.saveMessage(message);
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages(){
        return  messageService.getAllMessages();
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteMessage(@PathVariable UUID id){
        return messageService.deleteMessage(id);
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @DeleteMapping
    public ResponseEntity<String> deleteAllMessages(){
        return messageService.deleteAllMessages();
    }

}
