package com.bsuir.diploma.repository;

import com.bsuir.diploma.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
