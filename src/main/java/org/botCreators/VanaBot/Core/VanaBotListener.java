package org.botCreators.VanaBot.Core;

import java.time.ZonedDateTime;
import java.util.HashMap;

import org.botCreators.VanaBot.Utility.CommandParser;
import org.botCreators.VanaBot.Utility.RssReader;
import org.botCreators.VanaBot.Utility.StreamingHelper;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.user.GenericUserPresenceEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class VanaBotListener extends ListenerAdapter {

	CommandParser parser = new CommandParser();
	private EventWaiter waiter;
	RssReader rss = new RssReader();
	private StreamingHelper streamer = new StreamingHelper();
	private HashMap<String, ZonedDateTime> streamersList;
	
	
	public VanaBotListener(EventWaiter waiter){
		this.waiter = waiter;
		rss.readAndPrintRss();
		streamersList = new HashMap<>();
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
    	
    	if (!event.isRelationshipUpdate() && !event.getUser().isBot()){
			if (null != event.getMember().getGame() && Game.isValidStreamingUrl(event.getMember().getGame().getUrl())) {
				if (!streamersList.containsKey(event.getUser().getId()) || hasItBeenAnHour(event)){
					streamersList.put(event.getUser().getId(), ZonedDateTime.now());
	    			streamer.GetAndPrintStreamInfo(event);
				}
    		} else {
    			Role r = event.getGuild().getRolesByName("now streaming", true).get(0);
    			if (event.getMember().getRoles().contains(r)){
    				event.getGuild().getController().removeRolesFromMember(event.getMember(), r).queue();
    			}
    		}
    		
    	}
    }
    
    private boolean hasItBeenAnHour(GenericUserPresenceEvent event){
    	
    	if (!streamersList.containsKey(event.getUser().getId()))
    		return false;
    	else {
    		String userId = event.getUser().getId();
    		ZonedDateTime zdt = streamersList.get(userId);
    		
    		if (zdt.plusHours(1).isBefore(ZonedDateTime.now())){//If it hasn't been an hour, don't resend the streaming embed
    			return true;
    		}
    	}
    	    		
    	return false;    	
    }
}
