
package com.saultech.dotaitest.service;

import com.saultech.dotaitest.domain.UserAccessLog;
import com.saultech.dotaitest.repository.UserAccessLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileReaderServiceImpl implements FileReaderService{
    private final UserAccessLogRepository userAccessLogRepository;
    @Override
    public void readFile() {
        try {
            File myObj = new File("src/main/resources/user_access.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] splitData = data.split("\\|");
                String date = splitData[0];
                String ip = splitData[1];
                String request = splitData[2].replace("\"", "");
                String status = splitData[3].replace(": ", "");
                String userAgent = splitData[4].replace("\"", "");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
                LocalDateTime dateTime = LocalDateTime.parse(date, formatter);

                UserAccessLog userAccessLog = UserAccessLog.builder()
                        .date(dateTime)
                        .ip(ip)
                        .request(request)
                        .status(status)
                        .userAgent(userAgent)
                        .createdAt(LocalDateTime.now())
                        .build();
//                UserAccessLog savedUserAccessLog = userAccessLogRepository.save(userAccessLog);
//                log.info(savedUserAccessLog.toString());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            log.error("An error occurred: ", e);
            e.printStackTrace();
        }
    }

    @Override
    public void writeFile() {
        // TODO document why this method is empty
    }

    @Override
    public void processFile() {
        // TODO document why this method is empty
    }

    @Override
    public void processLine(String line) {
        // TODO document why this method is empty
    }

    @Override
    public void processLine(String line, String ip) {
        // TODO document why this method is empty
    }

    @Override
    public void processLine(String line, String ip, String request) {

    }

    @Override
    public void processLine(String line, String ip, String request, String status) {

    }

    @Override
    public void processLine(String line, String ip, String request, String status, String userAgent) {

    }
}
