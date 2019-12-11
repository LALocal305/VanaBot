package org.botCreators.VanaBot.Utility;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;


public class RssReader {

	private static EmbedHelper em = new EmbedHelper();
	private static String channelName;
	private static String channelId;
	
	public RssReader(){
		channelName = null;
		channelId = null;
	}
	
	public static void readAndPrintRss(MessageReceivedEvent event){
		ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
		ses.scheduleAtFixedRate(new Runnable() {
		    @Override
		    public void run() {
		    	System.out.println("Hourly run of News & Topics");
		    	printNews(event);
		    	printTopics(event);
		    }
		}, 30, 3600, TimeUnit.SECONDS);
	}
	
	private static void printNews(MessageReceivedEvent event){
	     try {
	    	 String newsFile = "rss/pastNews.rss";
	    	 List<String> datesFromRss = null;
	    	 
	         URL feedUrl = new URL("http://www.playonline.com/ff11us/polnews/news.xml");
	
	         SyndFeedInput input = new SyndFeedInput();
	         SyndFeed feed = input.build(new XmlReader(feedUrl));
	         
	         ArrayList<SyndEntry> entries = (ArrayList<SyndEntry>) feed.getEntries();	         
	         ArrayList<RssEntry> rss_entries = new ArrayList<>();
	         
	         try {
	        	 datesFromRss = Files.readAllLines(Paths.get(newsFile));
	         } catch (Exception e){
	        	 System.out.println("Unable to read News rss.");
	         }
	         
	         Date fromFileDate = null;
	         SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");//Fri Apr 27 10:44:56 PDT 2018
	         if (null != datesFromRss && datesFromRss.size() > 0){
	        	 fromFileDate = formatter.parse(datesFromRss.get(0));
	         }
	         
	         for(int i=0; i<entries.size();i++){
	        	 RssEntry t = new RssEntry(entries.get(i).getLink(),
	        			 entries.get(i).getDescription().getValue(), entries.get(i).getTitle(),
	        			 entries.get(i).getPublishedDate());
	        	 t.replaceHTMLTags();
	        	 
	        	 if ( null != fromFileDate && t.getPubDate().after(fromFileDate)){
	        		 if (i == 0) {
	        			 Files.write(Paths.get(newsFile), (t.getPubDate() + System.lineSeparator()).getBytes("UTF-8"),StandardOpenOption.CREATE,StandardOpenOption.WRITE);
	        			 System.out.println("N: now " + LocalDateTime.now());
	    	        	 System.out.println("N: file date " + fromFileDate);
	        			 System.out.println("N: tpubdate " + t.getPubDate());
	        		 }
	        		 rss_entries.add(t);
	        	 } 
	         }
	         
	         if(null != channelName && rss_entries.size() > 0){
	        	 for(int j=0;j<rss_entries.size();j++){
	        		 event.getJDA().getTextChannelById(channelId).sendMessage(em.BuildRssEmbed(rss_entries.get(j))).queue();
	        	 }
	         }
	         System.out.println("RSS News Entries printing: " + rss_entries.size());
		 }
	     catch (Exception ex) {
	         ex.printStackTrace();
	         System.out.println("ERROR: "+ex.getMessage());
	     }
	}
	
	private static void printTopics(MessageReceivedEvent event){
	     try {
	    	 String newsFile = "rss/pastTopics.rss";
	    	 List<String> datesFromRss = null;
	    	 
	         URL feedUrl = new URL("http://www.playonline.com/pcd/topics/ff11us/topics.xml");
	
	         SyndFeedInput input = new SyndFeedInput();
	         SyndFeed feed = input.build(new XmlReader(feedUrl));
	         
	         ArrayList<SyndEntry> entries = (ArrayList<SyndEntry>) feed.getEntries();	         
	         ArrayList<RssEntry> rss_entries = new ArrayList<>();
	         
	         try {
	        	 datesFromRss = Files.readAllLines(Paths.get(newsFile));
	         } catch (Exception e){
	        	 System.out.println("Unable to read Topics rss.");
	         }
	         
	         Date fromFileDate = null;
	         SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");//Thu, 26 Apr 2018 00:00:00 GMT+09:00
	         if (null != datesFromRss && datesFromRss.size() > 0){
	        	 fromFileDate = formatter.parse(datesFromRss.get(0));
	         }
	         
	         for(int i=0; i<entries.size();i++){
	        	 RssEntry t = new RssEntry(entries.get(i).getLink(),
	        			 entries.get(i).getDescription().getValue(), entries.get(i).getTitle(),
	        			 entries.get(i).getPublishedDate());
	        	 t.replaceHTMLTags();
	        	 
	        	 if ( null != fromFileDate && t.getPubDate().after(fromFileDate)){
	        		 if(i == 0) {
	        			 Files.write(Paths.get(newsFile), (t.getPubDate() + System.lineSeparator()).getBytes("UTF-8"),StandardOpenOption.CREATE,StandardOpenOption.WRITE);
	        			 System.out.println("T: now " + LocalDateTime.now());
	    	        	 System.out.println("T: file date " + fromFileDate);
	        			 System.out.println("T: tpub date " + t.getPubDate());
	        		 }
	        		 rss_entries.add(t);
	        	 } 
	         }
	         
	         if(null != channelName && rss_entries.size() > 0){
	        	 for(int j=0;j<rss_entries.size();j++){
	        		 event.getJDA().getTextChannelById(channelId).sendMessage(em.BuildRssEmbed(rss_entries.get(j))).queue();
	        	 }
	         }
	         System.out.println("RSS Topics Entries printing: " + rss_entries.size());
		 }
	     catch (Exception ex) {
	         ex.printStackTrace();
	         System.out.println("ERROR: "+ex.getMessage());
	     }
	}
	 
	public static void setRssChannel(MessageChannel channel) {
		channelName = channel.getName();
		channelId = channel.getId();
	}
	
	public static String getRssChannel() {
		if (null == channelName)
			return "not set.";
		else
			return channelName; 
	}
	
	public static String getRssChannelId() {
		return channelId;
	}
	
	public static void forceRunNewsAndTopics(MessageReceivedEvent event) {
		System.out.println("Forcing update for RSS feed.");
		printNews(event);
		printTopics(event);
	}
}
