package org.botCreators.VanaBot.Commands;

import java.util.HashMap;

import org.botCreators.VanaBot.Utility.Helper;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.managers.Presence;

public class PresenceCommand implements Command {

	private HashMap<String, String> argMap;
	
	@Override
	public void onCommand(MessageReceivedEvent event, String[] args, String command, EventWaiter waiter) {
		
		argMap = new HashMap<>(Helper.parseArgs(args));
		
		if(argMap.containsKey(command) && !argMap.get(command).isEmpty()){
	        Presence presence = event.getJDA().getPresence();
	        presence.setActivity(Activity.of(Activity.ActivityType.DEFAULT,argMap.get(command)));
	        
	        if(argMap.containsKey("-n") && !argMap.get("-n").isEmpty()){
	        	String status = argMap.get("-n");
	        	if (status.equals("dnd")){
	        		System.out.println(status);
	        		presence.setStatus(OnlineStatus.DO_NOT_DISTURB);
	        	} else if (status.equals("idle")){
	        		presence.setStatus(OnlineStatus.IDLE);
	        	} else {
	        		presence.setStatus(OnlineStatus.ONLINE);
	        	}
	        }
	        

		} else {
			event.getChannel().sendMessage("You forgot to provide me a game name.").queue();
		}
		
		event.getChannel().deleteMessageById(event.getMessage().getId()).queue();
	}

}
