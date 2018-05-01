package org.botCreators.VanaBot.Utility;

import org.botCreators.VanaBot.Commands.BGCommand;
import org.botCreators.VanaBot.Commands.ClockCommand;
import org.botCreators.VanaBot.Commands.HelpCommand;
import org.botCreators.VanaBot.Commands.IamCommand;
import org.botCreators.VanaBot.Commands.IamnotCommand;
import org.botCreators.VanaBot.Commands.MeCommand;
import org.botCreators.VanaBot.Commands.PresenceCommand;
import org.botCreators.VanaBot.Commands.RandomCommand;
import org.botCreators.VanaBot.Commands.RolesCommand;
import org.botCreators.VanaBot.Commands.RssSettingsCommand;
import org.botCreators.VanaBot.Commands.StatsCommand;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandParser {

	private Helper helper;
	private IamCommand iam;
	private ClockCommand clock;
	private RandomCommand ran;
	private HelpCommand help;
	private IamnotCommand iamnot;
	private RolesCommand roles;
	private BGCommand bg;
	private PresenceCommand presence;
	private RssSettingsCommand rss;
	private MeCommand me;
	private StatsCommand stats;
	
	
	private String[] parsed;
	public CommandParser() {
		helper = new Helper();
		iam = new IamCommand();
		clock = new ClockCommand();
		ran = new RandomCommand();
		help = new HelpCommand();
		iamnot = new IamnotCommand();
		roles = new RolesCommand();
		bg = new BGCommand();
		presence = new PresenceCommand();
		rss = new RssSettingsCommand();
		me = new MeCommand();
		stats = new StatsCommand();
	}

	public void Forward(MessageReceivedEvent event, String args, EventWaiter waiter) {
		
		parseCommand(args);
		
		String command = "";
		
		if(parsed[0].contains(" ")) { //ex. ?inv add this
			command = parsed[0].substring(0, parsed[0].indexOf(" ")+1).trim(); //command = ?inv
		} else { //ex. ?inv
			command = parsed[0];
		}
		
		
		if(event.isFromType(ChannelType.TEXT) && 
				(event.getChannel().getName().equals("bot-spam") || event.getChannel().getName().equals("testing"))){

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
			
			if(command.equals("bg")){
				bg.onCommand(event, parsed, command, waiter);
			}
			
			if(command.equals("me")){
				me.onCommand(event, parsed, command, waiter);
			}
			
			if(command.equals("stats")){
				stats.onCommand(event, parsed, command, waiter);
			}
			
			if(command.equals("presence") && helper.hasRole(event.getMember(), "mods", event.getGuild().getRoles())){
				presence.onCommand(event, parsed, command, waiter);
			}
			
			if(command.equals("rss") && helper.hasRole(event.getMember(), "mods", event.getGuild().getRoles())){
				rss.onCommand(event, parsed, command, waiter);
			}
		}
		
	}
	
	private String[] parseCommand(String args){
		if(args.contains("-n ")/* || args.contains("-q ") || args.contains("-s") || args.contains("-c") || args.contains("-d ")
				 || args.contains("-tag ") || args.contains("-w ") || args.contains("-rar ") || args.contains("-v ") || args.contains("-d1 ")
				 || args.contains("-d2 ") || args.contains("-dt ") || args.contains("-r ") || args.contains("-p ") || args.contains("-ac ")
				 || args.contains("-nn ")*/){
			return parsed = args.split("(?=-n )"/*|(?=-t )|(?=-d )|(?=-q )|(?=-w )|(?=-tag )|(?=-c)|(?=-v )|(?=-rar )|(?=-d1 )"
					+ "|(?=-d2 )|(?=-dt )|(?=-r )|(?=-p )|(?=-ac )|(?=-nn )|(?=-s)"*/);
		} else {
			return parsed = new String[] {args.trim()};
		}
	}
}
