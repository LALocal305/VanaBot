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
			.append("Bot is only available in the #bot-spam channel.\n\n")
			.append("iam - Assigns the desired nation as a role.\n")
			.append("iamnot - Unassigns the desired nation as a role. For San d'Oria use 'sandoria'.\n")
			.append("roles - Shows assignable roles.\n")
			.append("random - Rolls a 1000-sided die!\n")
			.append("help - Shows this message.\n")
			.append("clock - Shows current Vana'diel time.\n");
						
		event.getChannel().sendMessage(em.BuildSimpleEmbed(event, "Help", help.toString(), Color.CYAN)).queue();

	}

}
/* Current Help
Helper bot for /r/FFXI Discord server!
Bot is only available in the bot-spam channel.

​No Category:
  iamnot Unassigns the desired nation as a role. For San d'Oria use 'sandoria'.
  iam    Assigns the desired nation as a role.
  roles  Shows assignable roles.
  random Rolls a 1000-sided die!
  help   Shows this message.
  greet  Make me say hello!
  clock  Shows current Vana'diel time

Type !help command for more info on a command.
You can also type !help category for more info on a category.
*/