package org.botCreators.VanaBot.Commands;

import java.awt.Color;
import java.util.HashMap;

import org.botCreators.VanaBot.Utility.EmbedHelper;
import org.botCreators.VanaBot.Utility.Helper;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class GuidesCommand implements Command{
	private HashMap<String, String> argMap;
	private EmbedHelper em;
	
	@Override
	public void onCommand(MessageReceivedEvent event, String[] args, String command, EventWaiter waiter) {
		argMap = Helper.parseArgs(args);
		em = new EmbedHelper();
		
		String URL = "https://www.bg-wiki.com/bg/Category:Guides";

		event.getChannel().sendMessage(em.BuildSimpleEmbed(event, "Guides", URL, Color.GRAY)).queue();
		
		
	}
}
