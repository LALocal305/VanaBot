package org.botCreators.VanaBot.Commands;

import java.awt.Color;

import org.botCreators.VanaBot.Utility.EmbedHelper;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class HelpCommand implements Command {

	private EmbedHelper em;
	@Override
	public void onCommand(MessageReceivedEvent event, String[] args, String command, EventWaiter waiter) {
		em = new EmbedHelper();
		
		StringBuilder help = new StringBuilder();
		
		help.append("Helper Bot for /r/FFXI Discord server!\n")
			.append("Bot is only available in the #bot-spam channel.\n")
			.append("To use commands type `!` and the command. Ex. `!help`\n\n")
			.append("iam - Assigns the desired nation as a role.\n")
			.append("iamnot - Unassigns the desired nation as a role.\n")
			.append("roles - Shows assignable roles.\n")
			.append("random - Rolls a 1000-sided die!\n")
			.append("help - Shows this message.\n")
			.append("clock - Shows current Vana'diel time.\n")
			.append("bg - Will link to a BG-Wiki search page.\n")
			.append("stats - Shows server population and role breakdown.\n")
			.append("me - Shows your roles and join date.\n");
						
		event.getChannel().sendMessage(em.BuildSimpleEmbed(event, "Help", help.toString(), Color.CYAN)).queue();

	}

}