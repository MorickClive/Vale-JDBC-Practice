package com.mc.main.ims;

import com.mc.main.ims.controllers.CRUDController;
import com.mc.main.ims.controllers.PersonController;
import com.mc.main.ims.ui.CRUD;
import com.mc.main.ims.ui.Domain;
import com.mc.main.ims.ui.MainMenu;
import com.mc.main.ims.util.Console;
import com.mc.main.ims.util.DatabaseConnection;
import com.mc.main.ims.util.ReportFormatter;

public class IMS implements AutoCloseable {
	
	private boolean exitFlag;
	private CRUDController<?> domain;
	
	public void start() {
		DatabaseConnection.runSchema("Schema-Person.sql");
		
		menu();
		
		System.out.println("\nExiting Application...");
	}
	
	public void menu() {
		exitFlag = false;
		Integer choice;
		
		while(!exitFlag) {
			choiceMenu("Information Management System", Domain.class);
			choice = Console.inputInt();
			
			switch(choice) {
				case 1:
					domain = new PersonController();
					domainAction();
					break;
				case 2:
					System.out.println("[INFO]: NOTE_TRACKER pending implementation...\n");
					break;
					
				case 0:
					exitFlag = true;
					break;
					
				default:
					domain = null;
					System.out.println("Input error detected, please enter a valid choice.\n");
			}
		}
	}
	
	public void domainAction() {
		Integer choice;
		boolean localFlag = false;
		
		while(!localFlag) {
			choiceMenu(domain.genericToString().toUpperCase() + " MENU:", CRUD.class);
			choice = Console.inputInt();
			
			switch(choice) {
				case 1:
					domain.create();
					break;
				case 2:
					domain.read();
					break;
				case 3:
					domain.readAll();
					break;
				case 4:
					domain.update();
					break;
				case 5:
					domain.delete();
					break;
				case 0:
					localFlag = true;
					break;
					
				default:
					System.out.println("Input error detected, please enter a valid choice.");
			}
		}
		System.out.println("\n!!! Returning to Application Menu !!!\n");
	}	
	
	private void choiceMenu(String header, Class<? extends Enum> menu) {
		
		int counter = 1;
		boolean isMainMenu = menu.getDeclaredAnnotation(MainMenu.class) != null;
		StringBuilder sb = new StringBuilder();
		Enum[] options = menu.getEnumConstants();
		
		for(Enum option : options) {
			sb.append( String.format("\t%d). %s\n", counter++, option) );
		}
		
		sb.append( String.format("%s\n\t0). %s", "=".repeat(40) ,isMainMenu ? "EXIT" : "RETURN"));
		
		ReportFormatter.reportDivided(header, sb.toString());
		System.out.println("Please enter your choice:");
	}

	@Override
	public void close() throws Exception {
		DatabaseConnection.close();
		Console.close();
	}

}
