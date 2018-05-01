package org.botCreators.VanaBot.Utility;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
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

import net.dv8tion.jda.core.entities.MessageChannel;

public class RssReader {

	private EmbedHelper em = new EmbedHelper();
	private static MessageChannel rssChannel; 
	
	public RssReader(){
		rssChannel = null;
	}
	
	public void readAndPrintRss(){
		ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
		ses.scheduleAtFixedRate(new Runnable() {
		    @Override
		    public void run() {
		    	printNews();
		    	printTopics();
		    }
		}, 0, 6, TimeUnit.HOURS);
	}
	
	private void printNews(){
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
	        	 
	         }
	         
	         Date fromFileDate = null;
	         SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");//Fri Apr 27 10:44:56 PDT 2018
	         if (null != datesFromRss && datesFromRss.size() > 0){
	        	 fromFileDate = formatter.parse(datesFromRss.get(0));
	        	 System.out.println("file date " + fromFileDate);
	         }
	         
	         for(int i=0; i<entries.size();i++){
	        	 RssEntry t = new RssEntry(entries.get(i).getLink(),
	        			 entries.get(i).getDescription().getValue(), entries.get(i).getTitle(),
	        			 entries.get(i).getPublishedDate());
	        	 t.replaceHTMLTags();
	        	 System.out.println("tpubdate " + t.getPubDate());
	        	 
	        	 if ( null != fromFileDate && t.getPubDate().after(fromFileDate)){
	        		 Files.write(Paths.get(newsFile), (t.getPubDate() + System.lineSeparator()).getBytes("UTF-8"),StandardOpenOption.CREATE,StandardOpenOption.WRITE);
	        		 rss_entries.add(t);
	        	 } 
	         }
	         
	         if(null != rssChannel && rss_entries.size() > 0){
	        	 for(int j=0;j<rss_entries.size();j++){
	        		 rssChannel.sendMessage(em.BuildRssEmbed(rss_entries.get(j))).queue();
	        	 }
	         }
	         
		 }
	     catch (Exception ex) {
	         ex.printStackTrace();
	         System.out.println("ERROR: "+ex.getMessage());
	     }
	}
	
	private void printTopics(){
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
	        	 
	         }
	         
	         Date fromFileDate = null;
	         SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");//Thu, 26 Apr 2018 00:00:00 GMT+09:00
	         if (null != datesFromRss && datesFromRss.size() > 0){
	        	 fromFileDate = formatter.parse(datesFromRss.get(0));
	        	 System.out.println("file date " + fromFileDate);
	         }
	         
	         for(int i=0; i<entries.size();i++){
	        	 RssEntry t = new RssEntry(entries.get(i).getLink(),
	        			 entries.get(i).getDescription().getValue(), entries.get(i).getTitle(),
	        			 entries.get(i).getPublishedDate());
	        	 t.replaceHTMLTags();
	        	 System.out.println("tpubdate " + t.getPubDate());
	        	 
	        	 if ( null != fromFileDate && t.getPubDate().after(fromFileDate)){
	        		 Files.write(Paths.get(newsFile), (t.getPubDate() + System.lineSeparator()).getBytes("UTF-8"),StandardOpenOption.CREATE,StandardOpenOption.WRITE);
	        		 rss_entries.add(t);
	        	 } 
	         }
	         
	         if(null != rssChannel && rss_entries.size() > 0){
	        	 for(int j=0;j<rss_entries.size();j++){
	        		 rssChannel.sendMessage(em.BuildRssEmbed(rss_entries.get(j))).queue();
	        	 }
	         }
	         
		 }
	     catch (Exception ex) {
	         ex.printStackTrace();
	         System.out.println("ERROR: "+ex.getMessage());
	     }
	}
	 
	public static void setRssChannel(MessageChannel channel) {
		rssChannel = channel;
		System.out.println(channel.getName());
	}
}
