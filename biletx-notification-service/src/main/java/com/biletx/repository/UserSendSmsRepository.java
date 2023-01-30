package com.biletx.repository;

import com.biletx.model.UserSendSms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSendSmsRepository extends JpaRepository<UserSendSms, Integer> {
}
