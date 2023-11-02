package com.example.irrigationuniversity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    private String emailAddress;
    private String title;
    @Column(columnDefinition="TEXT")
    private String body;
    private String authorFirstname;
    private String authorLastname;
    private String telNumber;
    private String address;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
}
