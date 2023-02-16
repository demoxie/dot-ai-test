package com.saultech.dotaitest.service;

import com.saultech.dotaitest.domain.BlockedIpTable;
import com.saultech.dotaitest.domain.UserAccessLog;
import com.saultech.dotaitest.repository.BlockedIpTableRepository;
import com.saultech.dotaitest.repository.UserAccessLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserAccessLogServiceImpl implements UserAccessLogService{
    private final UserAccessLogRepository userAccessLogRepository;
    private final BlockedIpTableRepository blockedIpTableRepository;
    private static final String BLOCKED_COMMENT = "Blocked by the system";

    @Override
    public void queryUserAccessLog(String startDate, String duration, int limit) {
        LocalDateTime startDateTime = LocalDateTime.parse(startDate);
        LocalDateTime now = LocalDateTime.now();
        LocalDate startDateLocalDate = startDateTime.toLocalDate();
        LocalDate nowLocalDate = now.toLocalDate();
        if (duration.equals("hourly")) {
            List<UserAccessLog> whiteListed = userAccessLogRepository
                    .findAll().stream().filter(userAccessLog -> {
                        return (userAccessLog.getDate().isEqual(startDateTime) || userAccessLog.getDate().isAfter(startDateTime))
                                || (userAccessLog.getDate().isEqual(now) || userAccessLog.getDate().isBefore(now))
                                || userAccessLogRepository.countAllByIp(userAccessLog.getIp()) > limit;
                    }).toList();
            if (whiteListed.isEmpty()) {
                log.info("No IP found");
            } else {
                whiteListed.forEach(userAccessLog -> {
                    blockedIpTableRepository.save(BlockedIpTable.builder()
                            .ip(userAccessLog.getIp())
                            .comment(BLOCKED_COMMENT)
                            .requestNumber(userAccessLogRepository.countAllByIp(userAccessLog.getIp()))
                            .createdAt(LocalDateTime.now())
                            .build());
                    log.info(userAccessLog.toString());
                });
            }
            whiteListed.forEach(userAccessLog -> {
                blockedIpTableRepository.save(BlockedIpTable.builder()
                        .ip(userAccessLog.getIp())
                        .comment(BLOCKED_COMMENT)
                        .requestNumber(userAccessLogRepository.countAllByIp(userAccessLog.getIp()))
                        .createdAt(LocalDateTime.now())
                        .build());
                log.info(userAccessLog.toString());
            });
        } else if (duration.equals("daily")) {
            List<UserAccessLog> whiteListed = userAccessLogRepository
                    .findAll().stream().filter(userAccessLog -> {
                        return (userAccessLog.getDate().isEqual(startDateTime) || userAccessLog.getDate().isAfter(startDateTime))
                                || (userAccessLog.getDate().isEqual(now) || userAccessLog.getDate().isBefore(now))
                                || userAccessLogRepository.countAllByIp(userAccessLog.getIp()) > limit;
                    }).toList();
            if (whiteListed.isEmpty()) {
                log.info("No IP found");
            } else {
                whiteListed.forEach(userAccessLog -> {
                    blockedIpTableRepository.save(BlockedIpTable.builder()
                            .ip(userAccessLog.getIp())
                            .comment(BLOCKED_COMMENT)
                            .requestNumber(userAccessLogRepository.countAllByIp(userAccessLog.getIp()))
                            .createdAt(LocalDateTime.now())
                            .build());
                    log.info(userAccessLog.toString());
                });
            }
        }
    }
}
