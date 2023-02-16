package com.saultech.dotaitest;

import com.saultech.dotaitest.service.FileReaderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

@SpringBootApplication
@Slf4j
public class DotAiTestApplication {

    public static void main(String[] args) {
        try {
//"chmod 777 /home/runner/work/dot-ai-test/dot-ai-test/src/main/resources/access.log"
            runProcess(args[1]);
        }catch (Exception e) {
            e.printStackTrace();
        }
        SpringApplication.run(DotAiTestApplication.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner() {
        return args -> {
            log.info("Application started with command-line arguments: {}", Arrays.toString(args.getSourceArgs()));
            log.info("NonOptionArgs: {}", args.getNonOptionArgs());
            log.info("OptionNames: {}", args.getOptionNames());
        };
    }
    @Component
    @RequiredArgsConstructor(onConstructor = @__(@Autowired))
    public static class MyBean implements CommandLineRunner {
        private final FileReaderService fileReaderService;

        @Override
        public void run(String...args) throws Exception {
            log.info("App started with arguments: " + Arrays.toString(args));
            fileReaderService.readFile();
        }
    }
    private static void runProcess(String command) throws Exception {
        Process pro = Runtime.getRuntime().exec(command);
        printLines(command + " stdout:", pro.getInputStream());
        printLines(command + " stderr:", pro.getErrorStream());
        pro.waitFor();
        log.info(command + " exitValue() " + pro.exitValue());
    }
    private static void printLines(String cmd, InputStream ins) throws Exception {
        String line = null;
        BufferedReader in = new BufferedReader(
                new InputStreamReader(ins));
        while ((line = in.readLine()) != null) {
            log.info(cmd + " " + line);
        }
    }


}
