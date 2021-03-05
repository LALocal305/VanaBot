package org.botCreators.VanaBot.Commands;

import java.awt.Color;

import org.botCreators.VanaBot.Utility.EmbedHelper;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class _119Command implements Command {
	//private HashMap<String, String> argMap;
	private EmbedHelper em;
	
	@Override
	public void onCommand(MessageReceivedEvent event, String[] args, String command, EventWaiter waiter) {
		//argMap = Helper.parseArgs(args);
		em = new EmbedHelper();
		
		String URL = "https://www.bg-wiki.com/bg/Quickstart_1-119_Guide";

		event.getChannel().sendMessage(em.BuildSimpleEmbed(event, "Quickstart 1-119", URL, Color.GRAY)).queue();
		
		
	}
}
