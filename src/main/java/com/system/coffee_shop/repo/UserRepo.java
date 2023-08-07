package com.system.coffee_shop.repo;

import com.system.coffee_shop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    @Query(value = "SELECT * FROM users WHERE email = ?1", nativeQuery = true)
    Optional<User> findByEmail(String email);

    // 14/02/2023
    @Query(value = "SELECT * FROM users WHERE full_name = ?1", nativeQuery = true)
    User findByUsername(String fullName);

    @Query(value = "UPDATE users SET password =?1 WHERE email=?2",nativeQuery = true)
    void updatePassword(String updatedPassword, String email);

}
