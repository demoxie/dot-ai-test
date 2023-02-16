package com.saultech.dotaitest.repository;

import com.saultech.dotaitest.domain.UserAccessLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccessLogRepository extends JpaRepository<UserAccessLog, Long> {
    Optional<UserAccessLog> findByRequest(String request);

    Optional<UserAccessLog> findByIp(String ip);
    Optional<UserAccessLog> findByStatus(String status);

    long countAllByIp(String ip);
}
