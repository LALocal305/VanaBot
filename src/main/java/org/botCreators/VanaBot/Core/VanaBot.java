package org.botCreators.VanaBot.Core;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.security.auth.login.LoginException;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.managers.Presence;

public class VanaBot {

	public static void main(String[] args) {
		Properties log = new Properties();
    	
    	try (FileReader in = new FileReader("Properties/token.propConfs")) {
    		log.load(in);
    	} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	
    	String token = log.getProperty("token");
    	
    	
    	EventWaiter waiter = new EventWaiter();
        try
        {
            JDA jda = new JDABuilder(AccountType.BOT)
                    .setToken(token) 
                    .addEventListeners(waiter)
                    .addEventListeners(new VanaBotListener(waiter))
                    .build().awaitReady();  
            
            Presence presence = jda.getPresence(); 
            presence.setActivity(Activity.of(Activity.ActivityType.DEFAULT, "Tetra Master | !help"));
        }
        catch (LoginException e)
        {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

}

