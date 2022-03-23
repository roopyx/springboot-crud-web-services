package com.roopyx.springbootcrudrestfullwebservices.repository;

import com.roopyx.springbootcrudrestfullwebservices.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
