package org.botCreators.VanaBot.Utility;

import java.util.HashMap;

public class Helper {
	
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
}
