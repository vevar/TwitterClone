package com.example.twitterclone.repos;

import com.example.twitterclone.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByUsername(String username);

    User findByActivationCode(String code);
}
