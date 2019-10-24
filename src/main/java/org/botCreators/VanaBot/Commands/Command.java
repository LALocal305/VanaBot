package org.botCreators.VanaBot.Commands;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface Command {
	public void onCommand(MessageReceivedEvent event, String[] args, String command, EventWaiter waiter);
}
