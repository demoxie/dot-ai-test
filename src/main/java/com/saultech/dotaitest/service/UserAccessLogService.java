package com.saultech.dotaitest.service;

public interface UserAccessLogService {
    void queryUserAccessLog(String startDate, String duration, int limit);
}
