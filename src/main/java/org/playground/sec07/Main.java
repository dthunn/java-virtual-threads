package org.playground.sec07;

import org.playground.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.Executors;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

    }

    // w/o autocloseable - we have to issue shutdown for short-lived applications
    private static void withoutAutoCloseable(){
        var executorService = Executors.newSingleThreadExecutor();
        executorService.submit(Main::task);
        log.info("submitted");
        executorService.shutdown();
    }

    private static void withAutoCloseable(){
        try(var executorService = Executors.newSingleThreadExecutor()){
            executorService.submit(Main::task);
            executorService.submit(Main::task);
            executorService.submit(Main::task);
            executorService.submit(Main::task);
            log.info("submitted");
        }
    }

    private static void task(){
        CommonUtils.sleep(Duration.ofSeconds(1));
        log.info("task executed");
    }

}
