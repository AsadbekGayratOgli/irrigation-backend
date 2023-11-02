package com.example.irrigationuniversity.repository;
import com.example.irrigationuniversity.entity.Users;
import com.example.irrigationuniversity.projection.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
public interface UserRepository extends JpaRepository<Users, UUID> {
    UserDetails findByUsername(String username);

    @Query(value = "select id, firstname, lastname, username from users where id = ?1",nativeQuery = true)
    UserProjection findUserById(UUID id);
    @Query(value = "select u.id as id, firstname, lastname, username from users u join users_roles ur on u.id = ur.users_id join roles r on r.id = ur.roles_id where r.id = ?1 ",nativeQuery = true)
    List<UserProjection> findAllUsers(UUID roleId);
    @Modifying
    @Transactional
    @Query(value = "delete from users_roles where users_id = ?1",nativeQuery = true)
    void deleteUserRoles(UUID id);
    @Query(value = "select id, firstname, lastname, username from users where username = ?1",nativeQuery = true)
    UserProjection findUserDataByUsername(String username);
    @Query(value = "select case when roles_id != ?2  then true else false end from users_roles where users_id = ?1",nativeQuery = true)
    boolean checkUserRole(UUID id, UUID roleId);
}
