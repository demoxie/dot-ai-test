package com.saultech.dotaitest.service;

public interface FileReaderService {
    void readFile();
    void writeFile();
    void processFile();
    void processLine(String line);
    void processLine(String line, String ip);
    void processLine(String line, String ip, String request);
    void processLine(String line, String ip, String request, String status);
    void processLine(String line, String ip, String request, String status, String userAgent);

}
