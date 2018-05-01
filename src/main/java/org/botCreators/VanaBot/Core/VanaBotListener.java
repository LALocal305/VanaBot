package org.botCreators.VanaBot.Core;

import org.botCreators.VanaBot.Utility.CommandParser;
import org.botCreators.VanaBot.Utility.RssReader;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class VanaBotListener extends ListenerAdapter {

	CommandParser parser = new CommandParser();
	private EventWaiter waiter;
	RssReader rss = new RssReader();
	
	
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
}
