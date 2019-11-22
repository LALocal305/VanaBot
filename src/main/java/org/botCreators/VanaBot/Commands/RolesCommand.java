package org.botCreators.VanaBot.Commands;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.botCreators.VanaBot.Utility.CustomEmote;
import org.botCreators.VanaBot.Utility.EmbedHelper;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class RolesCommand implements Command {

	private EmbedHelper em;
	private HashMap<String, String> emoteMap;
	
	public RolesCommand(){
		emoteMap = new HashMap<>();
		emoteMap.put("adoulin", "Adoulin");
		emoteMap.put("ahturhgan", "Aht Urhgan");
		emoteMap.put("bastok", "Bastok");
		emoteMap.put("beastmen", "Beastmen");
		emoteMap.put("jeuno", "Jeuno");
		emoteMap.put("sandoria", "San d'Oria");
		emoteMap.put("tavnazia", "Tavnazia");
		emoteMap.put("windurst", "Windurst");
	}
	
	@Override
	public void onCommand(MessageReceivedEvent event, String[] args, String command, EventWaiter waiter) {
		em = new EmbedHelper();
		List<Emote> emotes = event.getGuild().getEmotes();
		ArrayList<CustomEmote> ems = new ArrayList<>();
		
		for(int i = 0; i < emotes.size(); i++){
			ems.add(new CustomEmote(emotes.get(i).getId(), emotes.get(i).getName()));
		}
		Collections.sort(ems);
		
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < ems.size(); j++) {
			String name = ems.get(j).getName();
			if (emoteMap.containsKey(name)){
				sb.append("<:")
				.append(name)
				.append(":")
				.append(ems.get(j).getId())
				.append("> ")
				.append(emoteMap.get(name))
				.append("\n");
			}
			
		}
		
		sb.append("\nYou may also assign any server name as a role.");
		
		event.getChannel().sendMessage(em.BuildSimpleEmbed(event, "Assignable Roles", sb.toString(), Color.BLUE)).queue();
		
		
	}

}
