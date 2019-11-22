package org.botCreators.VanaBot.Utility;

import java.awt.Color;
import java.util.List;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.user.update.GenericUserPresenceEvent;

public class EmbedHelper {
	
    public MessageEmbed BuildSimpleEmbed(MessageReceivedEvent event, String title, String desc, Color color){
    	EmbedBuilder embed = new EmbedBuilder();
    	
    	embed.setAuthor(event.getMember().getEffectiveName(), null, event.getAuthor().getEffectiveAvatarUrl());
    	embed.setColor(color);
    	embed.setTitle(title);
    	embed.setDescription(desc);
    	
    	return embed.build();
    }
    
    public MessageEmbed BuildStatsEmbed(MessageReceivedEvent event, String creationDate){
    	EmbedBuilder embed = new EmbedBuilder();
    	
    	embed.setAuthor(event.getJDA().getSelfUser().getName(), null, event.getJDA().getSelfUser().getEffectiveAvatarUrl());
    	StringBuilder sb = new StringBuilder();
    	
    	int members = event.getGuild().getMembers().size();
    	sb.append("Total Members: **").append(members).append("**\n");
    	
    	int sMembers = event.getGuild().getMembersWithRoles(event.getGuild().getRolesByName("San d'Oria", true)).size();
    	sb.append("San d'Oria citizens: **").append(sMembers).append("**\n");
    	
    	int wMembers = event.getGuild().getMembersWithRoles(event.getGuild().getRolesByName("Windurst", true)).size();
    	sb.append("Windurst citizens: **").append(wMembers).append("**\n");
    	
    	int bMembers = event.getGuild().getMembersWithRoles(event.getGuild().getRolesByName("Bastok", true)).size();
    	sb.append("Bastok citizens: **").append(bMembers).append("**\n");
    	
    	int jMembers = event.getGuild().getMembersWithRoles(event.getGuild().getRolesByName("Jeuno", true)).size();
    	sb.append("Jeuno citizens: **").append(jMembers).append("**\n");
    	
    	int beMembers = event.getGuild().getMembersWithRoles(event.getGuild().getRolesByName("Beastmen", true)).size();
    	sb.append("Beastmen: **").append(beMembers).append("**\n");
    	
    	int ahMembers = event.getGuild().getMembersWithRoles(event.getGuild().getRolesByName("Aht Urhgan", true)).size();
    	sb.append("Aht Urhgan citizens: **").append(ahMembers).append("**\n");
    	
    	int aMembers = event.getGuild().getMembersWithRoles(event.getGuild().getRolesByName("Adoulin", true)).size();
    	sb.append("Adoulin citizens: **").append(aMembers).append("**\n");
    	
    	sb.append("\nServer Population\n\n");
    	int asuraMembers = event.getGuild().getMembersWithRoles(event.getGuild().getRolesByName("Asura", true)).size();
    	sb.append("Asura: **").append(asuraMembers).append("**\n");
    	
    	int bahamutMembers = event.getGuild().getMembersWithRoles(event.getGuild().getRolesByName("Bahamut", true)).size();
    	sb.append("Bahamut: **").append(bahamutMembers).append("**\n");

    	int bismarckMembers = event.getGuild().getMembersWithRoles(event.getGuild().getRolesByName("Bismarck", true)).size();
    	sb.append("Bismarck: **").append(bismarckMembers).append("**\n");
    	
    	int carbuncleMembers = event.getGuild().getMembersWithRoles(event.getGuild().getRolesByName("Carbuncle", true)).size();
    	sb.append("Carbuncle: **").append(carbuncleMembers).append("**\n");
    	
    	int cerberusMembers = event.getGuild().getMembersWithRoles(event.getGuild().getRolesByName("Cerberus", true)).size();
    	sb.append("Cerberus: **").append(cerberusMembers).append("**\n");
    	
    	int fenrirMembers = event.getGuild().getMembersWithRoles(event.getGuild().getRolesByName("Fenrir", true)).size();
    	sb.append("Fenrir: **").append(fenrirMembers).append("**\n");
    	
    	int lakshmiMembers = event.getGuild().getMembersWithRoles(event.getGuild().getRolesByName("Lakshmi", true)).size();
    	sb.append("Lakshmi: **").append(lakshmiMembers).append("**\n");
    	
    	int leviathanMembers = event.getGuild().getMembersWithRoles(event.getGuild().getRolesByName("Leviathan", true)).size();
    	sb.append("Leviathan: **").append(leviathanMembers).append("**\n");
    	
    	int odinMembers = event.getGuild().getMembersWithRoles(event.getGuild().getRolesByName("Odin", true)).size();
    	sb.append("Odin: **").append(odinMembers).append("**\n");
    	
    	int phoenixMembers = event.getGuild().getMembersWithRoles(event.getGuild().getRolesByName("Phoenix", true)).size();
    	sb.append("Phoenix: **").append(phoenixMembers).append("**\n");
    	
    	int quetzMembers = event.getGuild().getMembersWithRoles(event.getGuild().getRolesByName("Quetzalcoatl", true)).size();
    	sb.append("Quetzalcoatl: **").append(quetzMembers).append("**\n");
    	
    	int ragnarokMembers = event.getGuild().getMembersWithRoles(event.getGuild().getRolesByName("Ragnarok", true)).size();
    	sb.append("Ragnarok: **").append(ragnarokMembers).append("**\n");
    	
    	int shivaMembers = event.getGuild().getMembersWithRoles(event.getGuild().getRolesByName("Shiva", true)).size();
    	sb.append("Shiva: **").append(shivaMembers).append("**\n");
    	
    	int sirenMembers = event.getGuild().getMembersWithRoles(event.getGuild().getRolesByName("Siren", true)).size();
    	sb.append("Siren: **").append(sirenMembers).append("**\n");
    	
    	int sylphMembers = event.getGuild().getMembersWithRoles(event.getGuild().getRolesByName("Sylph", true)).size();
    	sb.append("Sylph: **").append(sylphMembers).append("**\n");
    	
    	int valeforMembers = event.getGuild().getMembersWithRoles(event.getGuild().getRolesByName("Valefor", true)).size();
    	sb.append("Valefor: **").append(valeforMembers).append("**\n");
    	
    	
    	embed.addField("Server Stats", sb.toString(), false);
    	embed.setColor(Color.WHITE);
    	embed.setThumbnail("https://i.imgur.com/idl3ogB.jpg");   	
    	
    	if (null != creationDate) {
    		creationDate = creationDate.substring(0, creationDate.indexOf("."));
        	embed.setFooter("Server creation date: " + creationDate, null);
    	}
    	
    	return embed.build();
    }
    
    public MessageEmbed BuildMeEmbed(MessageReceivedEvent event, List<Role> roles, String date){
    	EmbedBuilder embed = new EmbedBuilder();
    	
    	embed.setAuthor(event.getMember().getEffectiveName(), null, event.getAuthor().getEffectiveAvatarUrl());
    	
    	if (null != roles && roles.size() >0) {
	    	StringBuilder rs = new StringBuilder();
	    	
    		for(int i = 0; i < roles.size(); i++){
    			rs.append(roles.get(i).getName()).append("\n");
	    	}
    		
    		embed.addField("Roles", rs.toString(), true);
    	}
    	
    	embed.setColor(Color.WHITE);
    	
    	if (null != date) {
    		date = date.substring(0, date.indexOf("."));
    		embed.addField("Join Date", date, true);
    	}
    	
    	
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
    
    public MessageEmbed BuildStreamingEmbed(GenericUserPresenceEvent event, 
    		String appName, String details, String url) {
    	EmbedBuilder embed = new EmbedBuilder();
    	
    	embed.setAuthor(event.getJDA().getSelfUser().getName(), null, 
    			event.getJDA().getSelfUser().getEffectiveAvatarUrl());
    	embed.setTitle(event.getMember().getEffectiveName() + " is now streaming!");
    	embed.setDescription("Come watch them play **" + details + "**!\n"+url);
    	embed.setColor(Color.decode("#DC27FF"));
    	embed.setThumbnail("https://i.imgur.com/OKo3YWu.png");//https://i.imgur.com/OKo3YWu.png (Twitch Logo)
    	
    	return embed.build();
    }
}
