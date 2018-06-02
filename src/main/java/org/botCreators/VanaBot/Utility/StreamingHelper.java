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
		String im = event.getMember().getGame().asRichPresence().getLargeImage().getUrl();
		
		if(appDetails.contains("Final Fantasy XI") && !appDetails.contains("XIV")){
			EmbedHelper em = new EmbedHelper();
			
			if (null != streamChannel){
				streamChannel.sendMessage(em.BuildStreamingEmbed(event, appName, appDetails, url, im)).queue();
				Role r = event.getGuild().getRolesByName("now streaming", true).get(0);
				event.getGuild().getController().addRolesToMember(event.getMember(), r).queue();
			}
		}
	
	}
	
	public static void setStreamChannel(MessageChannel channel) {
		streamChannel = channel;
	}
}