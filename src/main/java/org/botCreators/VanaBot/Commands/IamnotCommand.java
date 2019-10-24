package org.botCreators.VanaBot.Commands;

import java.util.HashMap;
import java.util.List;

import org.botCreators.VanaBot.Utility.Helper;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class IamnotCommand implements Command {

	private HashMap<String, String> argMap;
	
	@Override
	public void onCommand(MessageReceivedEvent event, String[] args, String command, EventWaiter waiter) {
		argMap = new HashMap<>(Helper.parseArgs(args));
		HashMap<String, String> roles = Helper.getNationNameMap();
		
		if(!argMap.get(command).isEmpty()) {
			String role = argMap.get(command).replace(" ", "").replaceAll("'", "").toLowerCase();
			
			if(null != role){
				
				if(!role.isEmpty()){
					List<Role> uRoles = event.getMember().getRoles();
					boolean hasRole = false;
					
					if(uRoles.size() > 0){
						for(int j = 0; j < uRoles.size(); j++){
							String strippedUserRole = uRoles.get(j).getName().replace(" ", "").replaceAll("'", "").toLowerCase();
							
							if (strippedUserRole.equals(role)){
								Role r = event.getGuild().getRolesByName(roles.get(role), true).get(0);
								event.getGuild().removeRoleFromMember(event.getMember(), r).queue();
								event.getChannel().sendMessage("You are no longer a member of " + r.getName() + ".").queue();
								hasRole = true;
								break;
							}
						}
					} else {
						event.getChannel().sendMessage("You don't have any roles assigned. Use `!iam NATION` to assign a role.").queue();
					}
					if (!hasRole) {
						event.getChannel().sendMessage("You don't have that role assigned.").queue();
					}
				}
			} else {
				event.getChannel().sendMessage("No such role found.").queue();
			}
		} else {
			event.getChannel().sendMessage("You need to supply a nation name.").queue();
		}

	}

}
