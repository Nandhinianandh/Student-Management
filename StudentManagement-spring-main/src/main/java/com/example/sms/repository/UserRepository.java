package com.example.sms.repository;



import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {


    User findUserByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    @Query("Select user from User user where user.email=:email")
    User findUserByEmail(@Param("email") String email);
    User findUserByUserId(Long userId);
}
