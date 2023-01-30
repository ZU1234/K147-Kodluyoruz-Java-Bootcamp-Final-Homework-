package com.biletx.repository;

import com.biletx.model.UserSendMail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSendMailRepository extends JpaRepository<UserSendMail, Integer> {
}
