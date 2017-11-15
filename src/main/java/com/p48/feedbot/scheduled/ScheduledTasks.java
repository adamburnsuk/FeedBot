package com.p48.feedbot.scheduled;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.p48.feedbot.services.FeedBot;
import com.rometools.rome.io.FeedException;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 50000000)
    public void reportCurrentTime() throws IllegalArgumentException, MalformedURLException, IOException, FeedException {
        log.info("The time is now {}", dateFormat.format(new Date()));
        
        FeedBot fb = new FeedBot();
		
		fb.savePodcastInfotoDB("D:\\toolbox\\feedbot\\feedbot\\src\\main\\java\\resources\\podcasts.json", "d:\\podcasts");
    }
}
