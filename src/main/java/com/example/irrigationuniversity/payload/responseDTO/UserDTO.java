package com.example.irrigationuniversity.payload.responseDTO;
import com.example.irrigationuniversity.projection.UserProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String token;
    private UserProjection user;
}
