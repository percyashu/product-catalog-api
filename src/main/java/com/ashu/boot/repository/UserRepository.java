package com.ashu.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashu.boot.dto.UserDTO;
import com.ashu.boot.models.User;
@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
