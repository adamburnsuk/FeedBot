package com.p48.feedbot.services;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.p48.feedbot.data.Podcast;
import com.p48.feedbot.data.PodcastRepository;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.ParsingFeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

/**
 * Feedbot class is used to download and manage podcasts from feeds.
 *
 */
public class FeedBot 
{
	
	@Autowired
	PodcastRepository pr;
	
    public void getPodcasts(String podcastList, String storageLocation) throws IllegalArgumentException, MalformedURLException, IOException, FeedException
    {
    	
    	Gson gson = new Gson();
    	JsonReader reader = new JsonReader(new FileReader(podcastList));
    	
    	Type listType = new TypeToken<List<Podcast>>(){}.getType();
    	List<Podcast> data = gson.fromJson(reader, listType);
    	
    	
    	for(Podcast podcast : data) {
    		
    		try {
    			
    		SyndFeed feed = new SyndFeedInput().build(new XmlReader(new URL(podcast.getUrl())));
    		System.out.println(feed.getFeedType());
    	
    		List<SyndEntry> itemList = feed.getEntries();
    		URL mp3Url = new URL(itemList.get(0).getEnclosures().get(0).getUrl());
    	
    		//Get most recent item
    		System.out.println(itemList.get(0).getTitle());
    		System.out.println(itemList.get(0).getEnclosures().get(0).getUrl());
    		
    		File mp3File = new File(storageLocation + "/" + podcast.getFileName());

    		//downloadFile(mp3Url, mp3File);
    		saveFile(mp3Url, storageLocation + "/" + podcast.getFileName());
    		}
    		catch(IOException | ParsingFeedException e) {
    			System.out.println(e.getMessage());
    		}
    	}
    	
    }
    
    public void savePodcastInfotoDB(String podcastList, String storageLocation) throws FileNotFoundException, IllegalArgumentException, FeedException {
    	
    	Gson gson = new Gson();
    	JsonReader reader = new JsonReader(new FileReader(podcastList));
    	
    	Type listType = new TypeToken<List<Podcast>>(){}.getType();
    	List<Podcast> data = gson.fromJson(reader, listType);
    	
    	
    	for(Podcast podcast : data) {
    		
    		try {
    		
    			pr.save(podcast);
    		
    		}
    		catch(Exception e) {
    			System.out.println(e.getMessage());
    		}
    	}
    }
    
    public static void downloadFile(URL mp3Url, File fileName) throws IOException {
    	//Copy the file
    	//TODO: set timeout for this function
    	FileUtils.copyURLToFile(mp3Url, fileName);
    }
    
    public static boolean saveFile(URL imgURL, String imgSavePath) {

        boolean isSucceed = true;

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(imgURL.toString());
        httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.11 Safari/537.36");
        httpGet.addHeader("Referer", "https://www.google.com");

        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity imageEntity = httpResponse.getEntity();

            if (imageEntity != null) {
                FileUtils.copyInputStreamToFile(imageEntity.getContent(), new File(imgSavePath));
            }

        } catch (IOException e) {
            isSucceed = false;
        }

        httpGet.releaseConnection();

        return isSucceed;
    }
}
