package com.example.irrigationuniversity.projection;

import java.time.LocalDateTime;
import java.util.UUID;

public interface CommentProjectionAdminSide {
    UUID getId();
    String getFirstname();
    String getLastname();
    String getBody();
    String getTelOrEmail();
    Boolean getVisible();
    LocalDateTime getCreated_at();
    UUID getContent_id();

}
