package org.botCreators.VanaBot.Commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.botCreators.VanaBot.Utility.EmbedHelper;
import org.botCreators.VanaBot.Utility.Helper;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class IamCommand implements Command {

	private HashMap<String, String> argMap;
	private ArrayList<String> unassignables = new ArrayList<>();
	private HashMap<String, String> assignableRolesNames;
	private ArrayList<String> worldRoleNames = new ArrayList<>();
	private EmbedHelper em;
	
	public IamCommand(){
		unassignables.add("mods");
		unassignables.add("bots");
		unassignables.add("NPC");
		unassignables.add("collaborators");
		
		assignableRolesNames = new HashMap<>();
		assignableRolesNames.put("sandoria", "San d'Oria");
		assignableRolesNames.put("windurst", "Windurst");
		assignableRolesNames.put("bastok", "Bastok");
		assignableRolesNames.put("beastmen", "Beastmen");
		assignableRolesNames.put("jeuno", "Jeuno");
		assignableRolesNames.put("ahturhgan", "Aht Urhgan");
		assignableRolesNames.put("tavnazia", "Tavnazia");
		assignableRolesNames.put("adoulin", "Adoulin");
		
		worldRoleNames.add("Asura"); 		worldRoleNames.add("Bahamut");
		worldRoleNames.add("Bismarck"); 	worldRoleNames.add("Carbuncle");
		worldRoleNames.add("Cerberus"); 	worldRoleNames.add("Fenrir");
		worldRoleNames.add("Lakshmi"); 		worldRoleNames.add("Leviathan");
		worldRoleNames.add("Odin"); 		worldRoleNames.add("Phoenix");
		worldRoleNames.add("Quetzalcoatl"); worldRoleNames.add("Ragnarok");
		worldRoleNames.add("Shiva");		worldRoleNames.add("Siren");
		worldRoleNames.add("Sylph"); 		worldRoleNames.add("Valefor");
	}
	
	@Override
	public void onCommand(MessageReceivedEvent event, String[] args, String command, EventWaiter waiter) {
		em = new EmbedHelper();
		argMap = new HashMap<>(Helper.parseArgs(args));
		
		if(!argMap.get(command).isEmpty()) {
			String role = argMap.get(command).replace(" ", "").replaceAll("'", "").toLowerCase();
			
			if(null != role && !unassignables.contains(role)){
				
				if(!role.isEmpty()){
					List<Role> uRoles = event.getMember().getRoles();
					boolean hasRole = false;
					
					if(uRoles.size() > 0){
						for(int j = 0; j < uRoles.size(); j++){
							String strippedUserRole = uRoles.get(j).getName().replace(" ", "").replaceAll("'", "").toLowerCase();
							
							if (strippedUserRole.equals(role)){
								hasRole = true;
								break;
							}
							
						}
					}
					if (doesUserHaveWorldRole(role)) { 
						
					} else
					if(assignableRolesNames.containsKey(role) && (!hasRole || uRoles.size() == 0)){
						Role r = event.getGuild().getRolesByName(assignableRolesNames.get(role), true).get(0);
						event.getGuild().addRoleToMember(event.getMember(), r).queue();
						
						String emote = "<:" + role + ":" + event.getGuild().getEmotesByName(role, true).get(0).getId() + ">";
						
						event.getChannel().addReactionById(event.getMessage().getId(), 
									event.getGuild().getEmotesByName(role, true).get(0)).queue();
						
						StringBuilder sb = new StringBuilder();
						sb.append(event.getMember().getEffectiveName()).append(" now proudly represents **").append(assignableRolesNames.get(role))
							.append("**! ").append(emote);
						
						event.getChannel().sendMessage(em.BuildNationEmbed(event, sb.toString(), r)).queue();
					} else if (hasRole){
						event.getChannel().sendMessage("You have the role already.").queue();
					} else {
						event.getChannel().sendMessage("That's not a role. Use `!roles` to see what is available.").queue();
					}
					
				} else {
					event.getChannel().sendMessage("You didn't supply a role.").queue();
				}
			} else {
				event.getChannel().sendMessage("That's not an assignable role. Use `!roles` to see what is available.").queue();
			}
		} else {
			event.getChannel().sendMessage("You need to supply a nation name.").queue();
		}
	}
	
	private boolean doesUserHaveWorldRole(String roleName) {
		for(int i = 0; i < worldRoleNames.size(); i++) {
			if (worldRoleNames.get(i).toLowerCase().equals(roleName.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

}