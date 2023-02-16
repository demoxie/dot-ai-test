package com.saultech.dotaitest.repository;

import com.saultech.dotaitest.domain.BlockedIpTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlockedIpTableRepository extends JpaRepository<BlockedIpTable, Long> {
    Optional<BlockedIpTable> findByIp(String ip);
}
