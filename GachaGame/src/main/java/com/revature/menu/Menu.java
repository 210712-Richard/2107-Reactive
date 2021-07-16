package com.revature.menu;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.beans.GachaObject;
import com.revature.beans.User;
import com.revature.services.UserService;
import com.revature.util.SingletonScanner;

// Encapsulate the user interface methods
public class Menu {

	private static final Logger log = LogManager.getLogger(Menu.class);
	
	private UserService us = new UserService();
	private User loggedUser = null;
	private Scanner scan = SingletonScanner.getScanner().getScan();
	
	public void start() {
		log.trace("Begin the GachaGame application. start()");
		mainLoop: while(true) {
			switch(startMenu()) {
			case 1:
				// login
				System.out.println("Please enter your username: ");
				String username = scan.nextLine();
				log.debug(username);
				// Call the user service to find the user we want.
				User u = us.login(username);
				if(u == null) {
					log.warn("Unsuccessful login attempt: "+ username);
					System.out.println("Please try again.");
				} else {
					loggedUser = u;
					System.out.println("Welcome back: "+u.getUsername());
					// call our next method (either the Player menu or the Admin menu, depending on user)
					log.info("Successful login for user: "+loggedUser);
					switch(loggedUser.getType()) {
					case PLAYER:
						player();
						break;
					case GAME_MASTER:
						master();
						break;
					}
				}
				break;
			case 2:
				// register
				break;
			case 3:
				// quit
				System.out.println("Goodbye!");
				break mainLoop;
			default:
				// invalid selection
				System.out.println("Not a valid selection, please try again.");
			}
		}
		log.trace("Ending start()");
	}
	
	private int startMenu() {
		log.trace("called startMenu()");
		System.out.println("Welcome to HistoryCats!");
		System.out.println("What would you like to do?");
		System.out.println("\t1. Login");
		System.out.println("\t2. Register");
		System.out.println("\t3. Quit");
		int selection = select();
		log.trace("Start menu returning selection: "+selection);
		return selection;
	}
	
	private void player() {
		log.trace("called player()");
		player: while(true) {
			switch(playerMenu()) {
			case 1:
				// daily bonus
				if(us.hasCheckedIn(loggedUser)) {
					System.out.println("Already checked in today, please try again tomorrow!");
					break;
				}
				us.doCheckIn(loggedUser);
				System.out.println("You gained "+GachaObject.DAILY_BONUS+" du-cats!");
				System.out.println("Your new total is "+loggedUser.getCurrency()+" du-cats!");
				break;
			case 2:
				// view currency
				System.out.println("You currently have "+loggedUser.getCurrency()+" du-cats.");
				break;
			case 3:
				// spend currency
				break;
			case 4:
				loggedUser = null;
				break player;
			default:
			}
		}
	}
	
	private int playerMenu() {
		System.out.println("What would you like to do?");
		System.out.println("\t1. Daily Bonus");
		System.out.println("\t2. See Currency");
		System.out.println("\t3. Summon");
		System.out.println("\t4. Logout");
		return select();
	}
	private void master() {
		master: while(true) {
			
		}
	}
	
	
	private int select() {
		int selection;
		try {
			selection = Integer.parseInt(scan.nextLine());
		} catch(Exception e) {
			selection = -1;
		}
		//log
		return selection;
	}

}
