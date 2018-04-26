package org.botCreators.VanaBot.Commands;

import java.util.HashMap;

import org.botCreators.VanaBot.Utility.Helper;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class BGCommand implements Command {
	//TODO look into getting the results - HttpUrlConnection etc
	private HashMap<String, String> argMap;
	
	@Override
	public void onCommand(MessageReceivedEvent event, String[] args, String command, EventWaiter waiter) {
		argMap = Helper.parseArgs(args);
		
		String URL = "https://www.bg-wiki.com/index.php?search=";
		String search = "";
		
		if (argMap.containsKey(command) && !argMap.get(command).isEmpty()){
			search = argMap.get(command).replace(" ", "+").toLowerCase();
			event.getChannel().sendMessage(URL + search).queue();
		} else {
			event.getChannel().sendMessage("You must supply a search term.").queue();
		}
		
		
	}

}
