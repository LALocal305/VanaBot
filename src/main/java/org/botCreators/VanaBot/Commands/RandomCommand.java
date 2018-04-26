package org.botCreators.VanaBot.Commands;

import java.util.Random;

import org.botCreators.VanaBot.Utility.EmbedHelper;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class RandomCommand implements Command {
	
	private EmbedHelper em;
		
	@Override
	public void onCommand(MessageReceivedEvent event, String[] args, String command, EventWaiter waiter) {
		
		em = new EmbedHelper();
		
		StringBuilder sb = new StringBuilder();
		Random r = new Random();
		int ran = r.nextInt(1000) + 1;
		sb.append(":game_die: ").append(event.getMember().getEffectiveName()).append(" rolls **").append(ran).append("**!");
		
		event.getChannel().sendMessage(em.BuildDiceRollEmbed(event, sb.toString())).queue();
	}

}
