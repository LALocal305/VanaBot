package org.botCreators.VanaBot.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

public class Helper {
	
	private static HashMap<String, String> emoteMap;
	
	public Helper(){
		emoteMap = new HashMap<>();
		emoteMap.put("sandoria", "San d'Oria");
		emoteMap.put("windurst", "Windurst");
		emoteMap.put("bastok", "Bastok");
		emoteMap.put("beastmen", "Beastmen");
		emoteMap.put("jeuno", "Jeuno");
		emoteMap.put("ahturhgan", "Aht Urhgan");
		emoteMap.put("tavnazia", "Tavnazia");
		emoteMap.put("adoulin", "Adoulin");
	}
	
	public static HashMap<String, String> parseArgs(String[] args){
		HashMap<String, String> tempMap = new HashMap<>();
		
		for(int i = 0; i < args.length; i++){
			String temp[] = args[i].split(" ", 2);
			
			if(temp.length > 1){
				temp[1] = temp[1].trim();
				tempMap.put(temp[0], temp[1]);
			} else {
				tempMap.put(temp[0], "");
			}
		}
		
		return tempMap;
	}
	
	public boolean hasRole(Member member, String roleName, List<Role> roles){
		ArrayList<String> roleNames = new ArrayList<>();
		
		for(int i = 0; i < roles.size(); i++){
			roleNames.add(roles.get(i).getName().toLowerCase());
		}
		
		return roleNames.contains(roleName);
	}
	
	public static String getProperNationName(String shortHand){
		return emoteMap.get(shortHand);
	}
	
	public static HashMap<String, String> getNationNameMap(){
		return emoteMap;
	}
}
