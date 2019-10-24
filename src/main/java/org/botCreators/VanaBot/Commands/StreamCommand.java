package org.botCreators.VanaBot.Commands;

import java.util.HashMap;
import java.util.List;

import org.botCreators.VanaBot.Utility.Helper;
import org.botCreators.VanaBot.Utility.StreamingHelper;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class StreamCommand implements Command {
	
	private HashMap<String, String> argMap;

	@Override
	public void onCommand(MessageReceivedEvent event, String[] args, String command, EventWaiter waiter) {

		argMap = new HashMap<>(Helper.parseArgs(args));
		
		if(!argMap.get(command).isEmpty()) {
			List<TextChannel> channels = event.getGuild().getTextChannels();
			boolean found = false;
			for(int i = 0; i < channels.size(); i++){
				if (channels.get(i).getName().toLowerCase().equals(argMap.get(command))){						
					StreamingHelper.setStreamChannel(channels.get(i));
					event.getChannel().sendMessage("Stream channel set to " + channels.get(i).getAsMention()).queue();
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
