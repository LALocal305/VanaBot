package org.botCreators.VanaBot.Commands;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import org.botCreators.VanaBot.Utility.EmbedHelper;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class StatsCommand implements Command {

	private EmbedHelper em;
	
	@Override
	public void onCommand(MessageReceivedEvent event, String[] args, String command, EventWaiter waiter) {
		em = new EmbedHelper();
		OffsetDateTime os = event.getGuild().getTimeCreated();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSZ");

		event.getChannel().sendMessage(em.BuildStatsEmbed(event, formatter.format(os))).queue();
	}

}
