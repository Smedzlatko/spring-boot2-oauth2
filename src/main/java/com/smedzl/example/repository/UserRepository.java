package com.smedzl.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smedzl.example.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findOneByUsername(String username);

	@Override
	Optional<User> findById(Long id);
}
