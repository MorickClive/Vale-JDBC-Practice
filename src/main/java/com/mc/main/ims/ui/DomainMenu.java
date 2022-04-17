package com.mc.main.ims.ui;

import com.mc.main.ims.controllers.CRUDController;
import com.mc.main.ims.controllers.ListController;
import com.mc.main.ims.controllers.NoteGroupController;
import com.mc.main.ims.controllers.PersonController;
import com.mc.main.ims.ui.options.CRUD;
import com.mc.main.ims.ui.options.Domain;
import com.mc.main.ims.ui.options.ListCRUD;
import com.mc.main.ims.ui.tags.MainMenu;
import com.mc.main.ims.util.Console;
import com.mc.main.ims.util.ReportFormatter;

public class DomainMenu {

	private CRUDController<?> domain;
	private int choice;
	private boolean exitFlag;
	
	public DomainMenu() {
		super();
	}
	
	public void run() {
		exitFlag = false;
		
		while(!exitFlag) {
			printOptions("Information Management System", Domain.class);
			choice = Console.inputInt();
			
			switch(choice) {
				case 1:
					domain = new PersonController();
					actions(domain);
					break;
				case 2:
					domain = new NoteGroupController();
					listActions((ListController<?>) domain);
					break;
					
				case 0:
					exitFlag = true;
					break;
					
				default:
					domain = null;
					System.out.println("Input error detected, please enter a valid choice.\n");
			}
			System.out.println("\n!!! Returning to Application Menu !!!\n");
		}
	}

	private void actions(CRUDController<?> domain) {
		boolean localFlag = false;
		
		while(!localFlag) {
			printOptions(domain.genericToString().toUpperCase() + " MENU:", CRUD.class);
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

	private void listActions(ListController<?> domain) {
		boolean localFlag = false;
		
		while(!localFlag) {
			printOptions(domain.genericToString().toUpperCase() + " MENU:", ListCRUD.class);
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
					actions(domain.modify());
					break;
				case 6:
					domain.delete();
					break;
				case 0:
					localFlag = true;
					break;
					
				default:
					System.out.println("Input error detected, please enter a valid choice.");
			}
		}	
	}
	
	private void printOptions(String header, Class<? extends Enum> menuOptions) {
		int counter = 1;
		boolean isMainMenu = menuOptions.getDeclaredAnnotation(MainMenu.class) != null;
		StringBuilder sb = new StringBuilder();
		Enum[] options = menuOptions.getEnumConstants();
		
		for(Enum option : options) {
			sb.append( String.format("\t%d). %s\n", counter++, option) );
		}
		
		sb.append( String.format("%s\n\t0). %s", "=".repeat(40) ,isMainMenu ? "EXIT" : "RETURN"));
		
		ReportFormatter.reportDivided(header, sb.toString());
		System.out.println("Please enter your choice:");
	}

}
