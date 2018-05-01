package org.botCreators.VanaBot.Commands;

import org.botCreators.VanaBot.Utility.EmbedHelper;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class StatsCommand implements Command {

	private EmbedHelper em;
	
	@Override
	public void onCommand(MessageReceivedEvent event, String[] args, String command, EventWaiter waiter) {
		em = new EmbedHelper();
		

		event.getChannel().sendMessage(em.BuildStatsEmbed(event)).queue();
	}

}
