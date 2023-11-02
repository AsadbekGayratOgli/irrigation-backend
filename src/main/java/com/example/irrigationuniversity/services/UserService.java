package com.example.irrigationuniversity.services;

import com.example.irrigationuniversity.entity.Users;
import com.example.irrigationuniversity.payload.responseDTO.UserDTO;
import com.example.irrigationuniversity.projection.UserProjection;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface UserService {
    ResponseEntity<List<UserProjection>> getUsers();

    ResponseEntity<?> addNewUser(Users users);

    ResponseEntity<String> deleteUser(UUID id);

    ResponseEntity<UserDTO> login(Users users);

    ResponseEntity<UserProjection> editUser(UUID id, Users user);
}
