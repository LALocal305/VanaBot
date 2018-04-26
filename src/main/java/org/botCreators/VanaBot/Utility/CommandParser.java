package org.botCreators.VanaBot.Utility;

import org.botCreators.VanaBot.Commands.ClockCommand;
import org.botCreators.VanaBot.Commands.HelpCommand;
import org.botCreators.VanaBot.Commands.IamCommand;
import org.botCreators.VanaBot.Commands.IamnotCommand;
import org.botCreators.VanaBot.Commands.RandomCommand;
import org.botCreators.VanaBot.Commands.RolesCommand;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandParser {
	
	private IamCommand iam;
	private ClockCommand clock;
	private RandomCommand ran;
	private HelpCommand help;
	private IamnotCommand iamnot;
	private RolesCommand roles;
	
	private String[] parsed;
	public CommandParser() {
		iam = new IamCommand();
		clock = new ClockCommand();
		ran = new RandomCommand();
		help = new HelpCommand();
		iamnot = new IamnotCommand();
		roles = new RolesCommand();
	}

	public void Forward(MessageReceivedEvent event, String args, EventWaiter waiter) {
		
		parseCommand(args);
		
		String command = "";
		
		if(parsed[0].contains(" ")) { //ex. ?inv add this
			command = parsed[0].substring(0, parsed[0].indexOf(" ")+1).trim(); //command = ?inv
		} else { //ex. ?inv
			command = parsed[0];
		}
		
		
		if(event.isFromType(ChannelType.TEXT)){ //TODO Limit to bot-spam channel
			
			if(command.equals("iam")){
				iam.onCommand(event, parsed, command, waiter);
			}
			
			if(command.equals("clock")){
				clock.onCommand(event, parsed, command, waiter);
			}
			
			if(command.equals("random")){
				ran.onCommand(event, parsed, command, waiter);
			}
			
			if(command.equals("help")) {
				help.onCommand(event, parsed, command, waiter);
			}
			
			if(command.equals("iamnot")){
				iamnot.onCommand(event, parsed, command, waiter);
			}
			
			if(command.equals("roles")){
				roles.onCommand(event, parsed, command, waiter);
			}
		}
		
	}
	
	private String[] parseCommand(String args){
		/*if(args.contains("-n ") || args.contains("-q ") || args.contains("-s") || args.contains("-c") || args.contains("-d ")
				 || args.contains("-tag ") || args.contains("-w ") || args.contains("-rar ") || args.contains("-v ") || args.contains("-d1 ")
				 || args.contains("-d2 ") || args.contains("-dt ") || args.contains("-r ") || args.contains("-p ") || args.contains("-ac ")
				 || args.contains("-nn ")){
			return parsed = args.split("(?=-n )|(?=-t )|(?=-d )|(?=-q )|(?=-w )|(?=-tag )|(?=-c)|(?=-v )|(?=-rar )|(?=-d1 )"
					+ "|(?=-d2 )|(?=-dt )|(?=-r )|(?=-p )|(?=-ac )|(?=-nn )|(?=-s)");
		} else {*/
			return parsed = new String[] {args.trim()};
		//}
	}
}
