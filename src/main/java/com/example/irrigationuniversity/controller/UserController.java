package com.example.irrigationuniversity.controller;

import com.example.irrigationuniversity.entity.Users;
import com.example.irrigationuniversity.payload.responseDTO.UserDTO;
import com.example.irrigationuniversity.projection.UserProjection;
import com.example.irrigationuniversity.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController  {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;

    }
    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    @PostMapping
    public ResponseEntity<?> addNewUser(@RequestBody Users user){
        return userService.addNewUser(user);
    }
    @PostMapping("/login")
    public ResponseEntity<UserDTO> getUserByUsername(@RequestBody Users users){
      return userService.login(users);
    }
    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    @GetMapping
    public ResponseEntity<List<UserProjection>> getUsers(){
        return userService.getUsers();
    }
    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    @DeleteMapping("{id}")
    public  ResponseEntity<String> deleteUser(@PathVariable UUID id){
        return userService.deleteUser(id);
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<UserProjection> editUser(@PathVariable UUID id,@RequestBody Users users){
        return userService.editUser(id,users);
    }
}
