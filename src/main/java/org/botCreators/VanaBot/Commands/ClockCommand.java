package org.botCreators.VanaBot.Commands;

import org.botCreators.VanaBot.Utility.EmbedHelper;
import org.botCreators.VanaBot.Utility.VanaClock;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class ClockCommand implements Command {

	private EmbedHelper em;
	
	@Override
	public void onCommand(MessageReceivedEvent event, String[] args, String command, EventWaiter waiter) {
		em = new EmbedHelper();
		
		VanaClock clock = new VanaClock();
	
		event.getChannel().sendMessage(em.BuildClockEmbed(event, clock.getTime())).queue();
	}

}
