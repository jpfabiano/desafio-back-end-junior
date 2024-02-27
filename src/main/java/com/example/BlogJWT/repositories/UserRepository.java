package com.example.BlogJWT.repositories;

import com.example.BlogJWT.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {
    UserDetails findByLogin (String login);

    UserDetails findByUsername (String username);
}
