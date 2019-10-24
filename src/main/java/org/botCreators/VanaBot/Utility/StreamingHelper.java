package org.botCreators.VanaBot.Utility;

import java.util.List;

import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.user.update.GenericUserPresenceEvent;

public class StreamingHelper {
	private static MessageChannel streamChannel; 
	
	public StreamingHelper(){
		streamChannel = null;
	}
	
	public void GetAndPrintStreamInfo(GenericUserPresenceEvent event){
		List<Activity> act = event.getMember().getActivities();
		String appName = act.get(0).asRichPresence().getName();// event.getMember().getActivities().asRichPresence().getName();
		String appDetails = act.get(0).asRichPresence().getDetails(); //event.getMember().getGame().asRichPresence().getDetails();
		String url = act.get(0).asRichPresence().getUrl();// event.getMember().getGame().asRichPresence().getUrl();
		
		if(appDetails.contains("Final Fantasy XI") && !appDetails.contains("XIV") && !appDetails.contains("XII")){
			EmbedHelper em = new EmbedHelper();
			
			if (null != streamChannel){
				streamChannel.sendMessage(em.BuildStreamingEmbed(event, appName, appDetails, url)).queue();
				Role r = event.getGuild().getRolesByName("now streaming", true).get(0);
				if (!event.getMember().getRoles().contains(r)){
					event.getGuild().addRoleToMember(event.getMember(), r).queue();
				} 
			}
		}
	
	}
	
	public static void setStreamChannel(MessageChannel channel) {
		streamChannel = channel;
	}
}