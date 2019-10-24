package org.botCreators.VanaBot.Utility;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.user.GenericUserPresenceEvent;

public class StreamingHelper {
	private static MessageChannel streamChannel; 
	
	public StreamingHelper(){
		streamChannel = null;
	}
	
	public void GetAndPrintStreamInfo(GenericUserPresenceEvent event){
		String appName = event.getMember().getGame().asRichPresence().getName();
		String appDetails = event.getMember().getGame().asRichPresence().getDetails();
		String url = event.getMember().getGame().asRichPresence().getUrl();
		
		if(appDetails.contains("Final Fantasy XI") && !appDetails.contains("XIV") && !appDetails.contains("XII")){
			EmbedHelper em = new EmbedHelper();
			
			if (null != streamChannel){
				streamChannel.sendMessage(em.BuildStreamingEmbed(event, appName, appDetails, url)).queue();
				Role r = event.getGuild().getRolesByName("now streaming", true).get(0);
				if (!event.getMember().getRoles().contains(r)){
					event.getGuild().getController().addRolesToMember(event.getMember(), r).queue();
				}
			}
		}
	
	}
	
	public static void setStreamChannel(MessageChannel channel) {
		streamChannel = channel;
	}
}