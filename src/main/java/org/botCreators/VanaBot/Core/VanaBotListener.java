package org.botCreators.VanaBot.Core;

import org.botCreators.VanaBot.Utility.CommandParser;
import org.botCreators.VanaBot.Utility.EmbedHelper;
import org.botCreators.VanaBot.Utility.RssReader;
import org.botCreators.VanaBot.Utility.StreamingHelper;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.user.GenericUserPresenceEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class VanaBotListener extends ListenerAdapter {

	CommandParser parser = new CommandParser();
	private EventWaiter waiter;
	RssReader rss = new RssReader();
	private StreamingHelper streamer = new StreamingHelper();
	
	
	public VanaBotListener(EventWaiter waiter){
		this.waiter = waiter;
		rss.readAndPrintRss();
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
			if (null != event.getMember().getGame() 
    				&& Game.isValidStreamingUrl(event.getMember().getGame().getUrl())) { 
    			streamer.GetAndPrintStreamInfo(event);
    		} else {
    			Role r = event.getGuild().getRolesByName("now streaming", true).get(0);
    			if (event.getMember().getRoles().contains(r)){
    				event.getGuild().getController().removeRolesFromMember(event.getMember(), r).queue();
    			}
    		}
    		
    	}
    }
}
