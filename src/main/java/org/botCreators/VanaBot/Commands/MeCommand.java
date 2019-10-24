package org.botCreators.VanaBot.Commands;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.botCreators.VanaBot.Utility.EmbedHelper;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class MeCommand implements Command{

	private EmbedHelper em;
	
	@Override
	public void onCommand(MessageReceivedEvent event, String[] args, String command, EventWaiter waiter) {
		em = new EmbedHelper();
		OffsetDateTime os = event.getMember().getTimeJoined();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSZ");
		List<Role> roles = event.getMember().getRoles();
		
		event.getChannel().sendMessage(em.BuildMeEmbed(event, roles, formatter.format(os))).queue();
		
	}

}
