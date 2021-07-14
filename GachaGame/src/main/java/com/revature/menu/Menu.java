package com.revature.menu;

import java.util.Scanner;

import com.revature.beans.User;
import com.revature.services.UserService;
import com.revature.util.SingletonScanner;

// Encapsulate the user interface methods
public class Menu {
	private UserService us = new UserService();
	private User loggedUser = null;
	private Scanner scan = SingletonScanner.getScanner().getScan();
	
	public void start() {
		
		mainLoop: while(true) {
			switch(startMenu()) {
			case 1:
				// login
				System.out.println("Please enter your username: ");
				String username = scan.nextLine();
				// Call the user service to find the user we want.
				User u = us.login(username);
				if(u == null) {
					System.out.println("Please try again.");
				} else {
					loggedUser = u;
					System.out.println("Welcome back: "+u.getUsername());
					// call our next method (either the Player menu or the Admin menu, depending on user)
					
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
	}
	
	public int startMenu() {
		//log
		System.out.println("Welcome to HistoryCats!");
		System.out.println("What would you like to do?");
		System.out.println("\t1. Login");
		System.out.println("\t2. Register");
		System.out.println("\t3. Quit");
		int selection;
		try {
			selection = Integer.parseInt(scan.nextLine());
		} catch(Exception e) {
			selection = -1;
		}
		//log
		return selection;
	}
	
	public void player() {
		
	}
	public void master() {
		
	}

}
