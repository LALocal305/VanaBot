package org.botCreators.VanaBot.Core;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.security.auth.login.LoginException;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Game.GameType;
import net.dv8tion.jda.core.managers.Presence;

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
    	
    	String token = log.getProperty("testToken");
    	
    	
    	EventWaiter waiter = new EventWaiter();
        try
        {
            JDA jda = new JDABuilder(AccountType.BOT)
                    .setToken(token) 
                    .addEventListener(waiter)
                    .addEventListener(new VanaBotListener(waiter))
                    .buildBlocking();  
            
            Presence presence = jda.getPresence();
            presence.setGame(Game.of(GameType.DEFAULT, "Tetra Master | !help"));
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

