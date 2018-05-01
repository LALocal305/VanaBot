package org.botCreators.VanaBot.Utility;

import java.awt.Color;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class EmbedHelper {
	
    public MessageEmbed BuildSimpleEmbed(MessageReceivedEvent event, String title, String desc, Color color){
    	EmbedBuilder embed = new EmbedBuilder();
    	
    	embed.setAuthor(event.getMember().getEffectiveName(), null, event.getAuthor().getEffectiveAvatarUrl());
    	embed.setColor(color);
    	embed.setTitle(title);
    	embed.setDescription(desc);
    	
    	return embed.build();
    }
    
    public MessageEmbed BuildRssEmbed(RssEntry entry){
    	EmbedBuilder embed = new EmbedBuilder();
    	
    	embed.setColor(Color.WHITE);
    	embed.setThumbnail("https://i.imgur.com/idl3ogB.jpg");
    	embed.setTitle(entry.getTitle(), entry.getLink());
    	embed.setDescription(entry.getDescription());
    	embed.setFooter(entry.getPubDate().toString(), null);
    	
    	return embed.build();
    }
    
    public MessageEmbed BuildNationEmbed(MessageReceivedEvent event, String desc, Role role){
    	EmbedBuilder embed = new EmbedBuilder();
    	
    	embed.setColor(role.getColor());
    	embed.setDescription(desc);
    	
    	return embed.build();
    }
    
    public MessageEmbed BuildDiceRollEmbed(MessageReceivedEvent event, String result) {
    	EmbedBuilder embed = new EmbedBuilder();
    	
    	embed.setAuthor(event.getMember().getEffectiveName(), null, event.getAuthor().getEffectiveAvatarUrl());
    	embed.setTitle("Dice Roll!");
    	embed.setDescription(result);
    	
    	return embed.build();
    }
    
    public MessageEmbed BuildClockEmbed(MessageReceivedEvent event, String time) {
    	EmbedBuilder embed = new EmbedBuilder();
    	
    	embed.setAuthor(event.getMember().getEffectiveName(), null, event.getAuthor().getEffectiveAvatarUrl());
    	embed.setTitle("Current Vana'Diel Time");
    	embed.setDescription(time);
    	
    	return embed.build();
    }
}
