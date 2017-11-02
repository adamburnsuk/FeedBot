package com.pride48.feedbot;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.p48.feedbot.services.FeedBot;
import com.rometools.rome.io.FeedException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FeedbotApplicationTests {

	@Test
	public void contextLoads() throws IllegalArgumentException, MalformedURLException, IOException, FeedException {
		
		FeedBot fb = new FeedBot();
		
		fb.getPodcasts("podcasts.json", "c:\\podcasts");
	}

}
