// User Repository
package com.SpringSecurity.UserRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringSecurity.UserEntity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {

	Optional<UserEntity> findByUsername(String username);
}