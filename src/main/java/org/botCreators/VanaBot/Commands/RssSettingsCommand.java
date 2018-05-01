package org.botCreators.VanaBot.Commands;

import java.util.HashMap;
import java.util.List;

import org.botCreators.VanaBot.Utility.Helper;
import org.botCreators.VanaBot.Utility.RssReader;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class RssSettingsCommand implements Command{

	private HashMap<String, String> argMap;
	
	@Override
	public void onCommand(MessageReceivedEvent event, String[] args, String command, EventWaiter waiter) {

		argMap = new HashMap<>(Helper.parseArgs(args));
		
		if(!argMap.get(command).isEmpty()) {
			List<TextChannel> channels = event.getGuild().getTextChannels();
			boolean found = false;
			for(int i = 0; i < channels.size(); i++){
				if (channels.get(i).getName().toLowerCase().equals(argMap.get(command))){						
					RssReader.setRssChannel(channels.get(i));
					event.getChannel().sendMessage("Channel set.").queue();
					found = true;
					break;
				}
				
			}
			
			if(!found){
				event.getChannel().sendMessage("I did not find that channel on this server.").queue();
			}
				
		
		} else {
			event.getChannel().sendMessage("You need to supply me with a valid channel name.").queue();
		}
		
	}

}
