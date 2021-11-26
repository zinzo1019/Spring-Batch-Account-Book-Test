package com.example.hellospringbatch.Repository;

import com.example.hellospringbatch.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
