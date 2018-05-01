package org.botCreators.VanaBot.Utility;

import java.util.Date;

public class RssEntry {
	
	private String link;
	private String description;
	private String title;
	private Date pubDate;
	
	public RssEntry(String link, String description, String title, Date pubDate) {
		super();
		this.link = link;
		this.description = description;
		this.title = title;
		this.pubDate = pubDate;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}
	
	public void replaceHTMLTags(){
		this.description = this.description.replace("<br>", "\n").replace("[", "**[").replace("]", "]**").replaceAll("<.*?>|&.*?;", "");
	}

	@Override
	public String toString() {
		return "RssEntry [link=" + link + ", description=" + description + ", title=" + title + ", pubDate=" + pubDate
				+ "]";
	}
	
	
}
