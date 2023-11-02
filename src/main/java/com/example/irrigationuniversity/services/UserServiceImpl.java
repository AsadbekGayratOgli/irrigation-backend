package com.example.irrigationuniversity.services;

import com.example.irrigationuniversity.entity.Roles;
import com.example.irrigationuniversity.entity.Users;
import com.example.irrigationuniversity.payload.responseDTO.UserDTO;
import com.example.irrigationuniversity.projection.UserProjection;
import com.example.irrigationuniversity.repository.RoleRepository;
import com.example.irrigationuniversity.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final AuthenticationConfiguration authenticationConfiguration;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private String adminRoleId = "bdaf00c4-13e0-4205-a019-6c18735d19ff";
    private String superAdminRoleId = "de2c85c8-7621-41db-a39c-bdc1959d8706";
    @Override
    public ResponseEntity<List<UserProjection>> getUsers(){
        return ResponseEntity.ok().body(userRepository.findAllUsers(UUID.fromString(adminRoleId)));
    }
    @Override
    public ResponseEntity<?> addNewUser(Users user){
        List<Roles> roles = roleRepository.findRolesListById(UUID.fromString(adminRoleId));
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            Users savedUser = userRepository.save(user);
            return ResponseEntity.ok().body(userRepository.findUserById(savedUser.getId()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("This username has been already taken!");
        }
    }
    @Override
    public ResponseEntity<String> deleteUser(UUID id){
        if(userRepository.checkUserRole(id,UUID.fromString(superAdminRoleId))){
         userRepository.deleteUserRoles(id);
         userRepository.deleteById(id);
         return ResponseEntity.ok().body("Successfully deleted!");
        }
        return ResponseEntity.ok().body("This user can not be deleted!");
    }
    @Override
    public ResponseEntity<UserDTO> login(Users users){
        try {
            System.out.println(users);
            UserDetails userDetails = userDetailsService.loadUserByUsername(users.getUsername());
            System.out.println(userDetails);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    users .getPassword(),
                    userDetails.getAuthorities()
            );
            authenticationConfiguration.getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
            String token = Jwts
                    .builder()
                    .setIssuer(userDetails.getUsername())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
                    .signWith(Keys.hmacShaKeyFor("adgffsagdafhadghsf24524jbk4htku24bgkw4g3g34h4kj3htjk".getBytes()),SignatureAlgorithm.HS256)
                    .compact();
            UserDTO userDTO = new UserDTO(token,userRepository.findUserDataByUsername(users.getUsername()));
            return ResponseEntity.ok().body(userDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
    @Override
    public ResponseEntity<UserProjection> editUser(UUID id, Users user){
        Optional<Users> oldUser = userRepository.findById(id);
        user.setRoles(oldUser.get().getRoles());
        if(user.getPassword()!=null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }else {
            user.setPassword(oldUser.get().getPassword());
        }
        user.setId(id);
        userRepository.save(user);
        return ResponseEntity.ok().body(userRepository.findUserById(id));
    }
}
