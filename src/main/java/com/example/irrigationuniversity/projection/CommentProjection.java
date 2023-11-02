package com.example.irrigationuniversity.projection;

import java.time.LocalDateTime;
import java.util.UUID;

public interface CommentProjection {
    UUID getId();
    String getFirstname();
    String getLastname();
    String getBody();
    LocalDateTime getCreated_at();

}
