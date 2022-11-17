package org.botCreators.VanaBot.Core;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;

import org.botCreators.VanaBot.Utility.CommandParser;
import org.botCreators.VanaBot.Utility.Helper;
import org.botCreators.VanaBot.Utility.RssReader;
import org.botCreators.VanaBot.Utility.StreamingHelper;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import net.dv8tion.jda.api.entities.Activity;
//import net.dv8tion.jda.api.entities.Game;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.user.update.GenericUserPresenceEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class VanaBotListener extends ListenerAdapter {

	CommandParser parser = new CommandParser();
	private EventWaiter waiter;
	RssReader rss = new RssReader();
	private StreamingHelper streamer = new StreamingHelper();
	private HashMap<String, ZonedDateTime> streamersList;
	
	
	public VanaBotListener(EventWaiter waiter){
		this.waiter = waiter;
		streamersList = new HashMap<>();
		Helper.init();
	}
	
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        if(!event.getAuthor().isBot())
        {
            String prefix = "!";
            String msg = event.getMessage().getContentRaw();
            
        	if (msg.startsWith(prefix) && msg.length() > prefix.length())
			{         		
        		if(msg.charAt(prefix.length()) == ' '){
        			
        		} else {
        			parser.Forward(event, msg.substring(prefix.length()), waiter);
        		}
			}
        }
    }
    
    public void onGenericUserPresence(GenericUserPresenceEvent event){ 
    	List<Activity> act = event.getMember().getActivities();
    	Activity streamAct = null;
    	
    	for(Activity a : act) {
    		if (a.getType() == Activity.ActivityType.STREAMING)
    			streamAct = a;
    	}
    	
    	if (null != streamAct) {
			if (null != event.getMember().getActivities() && Activity.isValidStreamingUrl(streamAct.asRichPresence().getUrl())) {
				if (!streamersList.containsKey(event.getMember().getId()) || hasItBeenAnHour(event)){
					streamersList.put(event.getMember().getId(), ZonedDateTime.now());
	    			streamer.GetAndPrintStreamInfo(event);
				}
    		} else {
    			Role r = event.getGuild().getRolesByName("now streaming", true).get(0);
    			if (event.getMember().getRoles().contains(r)){
    				event.getGuild().removeRoleFromMember(event.getMember(), r).queue();
    			}
    		}
    		
    	}
    }
    
    private boolean hasItBeenAnHour(GenericUserPresenceEvent event){
    	
    	if (!streamersList.containsKey(event.getMember().getId()))
    		return false;
    	else {
    		String userId = event.getMember().getId();
    		ZonedDateTime zdt = streamersList.get(userId);
    		
    		if (zdt.plusHours(1).isBefore(ZonedDateTime.now())){//If it hasn't been an hour, don't resend the streaming embed
    			return true;
    		}
    	}
    	    		
    	return false;    	
    }
}
