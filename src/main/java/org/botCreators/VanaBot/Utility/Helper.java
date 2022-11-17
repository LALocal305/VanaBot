package org.botCreators.VanaBot.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

public class Helper {
	
	private static HashMap<String, String> emoteMap;
	
	public Helper(){
	}
	
	public static void init() {
		emoteMap = new HashMap<>();
		emoteMap.put("sandoria", "San d'Oria");
		emoteMap.put("windurst", "Windurst");
		emoteMap.put("bastok", "Bastok");
		emoteMap.put("beastmen", "Beastmen");
		emoteMap.put("jeuno", "Jeuno");
		emoteMap.put("ahturhgan", "Aht Urhgan");
		emoteMap.put("tavnazia", "Tavnazia");
		emoteMap.put("adoulin", "Adoulin");
		
		emoteMap.put("asura","Asura"); 					emoteMap.put("bahamut","Bahamut");
		emoteMap.put("bismarck","Bismarck"); 			emoteMap.put("carbuncle","Carbuncle");
		emoteMap.put("cerberus","Cerberus"); 			emoteMap.put("fenrir","Fenrir");
		emoteMap.put("lakshmi","Lakshmi"); 				emoteMap.put("leviathan","Leviathan");
		emoteMap.put("odin","Odin"); 					emoteMap.put("phoenix","Phoenix");
		emoteMap.put("quetzalcoatl","Quetzalcoatl"); 	emoteMap.put("ragnarok","Ragnarok");
		emoteMap.put("shiva","Shiva");					emoteMap.put("siren","Siren");
		emoteMap.put("sylph","Sylph"); 					emoteMap.put("valefor","Valefor");
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
	
	public static boolean hasRole(Member member, String roleNames, List<Role> serverRoles){
		String[] necessaryRoles = roleNames.split(",");
		ArrayList<String> memberRoleNames =  new ArrayList<String>();
		
		
		for(int j = 0; j < serverRoles.size(); j++) {
			memberRoleNames.add(serverRoles.get(j).getName());
		}
		
		for(int i = 0; i < necessaryRoles.length; i++)
		{
			if (memberRoleNames.contains(necessaryRoles[i]))
				return true;
		}

		
		return false;
	}
	
	public static String getProperNationName(String shortHand){
		return emoteMap.get(shortHand);
	}
	
	public static HashMap<String, String> getNationNameMap(){
		return emoteMap;
	}
}
