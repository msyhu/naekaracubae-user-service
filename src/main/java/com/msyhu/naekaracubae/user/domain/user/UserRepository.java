package com.msyhu.naekaracubae.user.domain.user;

import com.msyhu.naekaracubae.user.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
