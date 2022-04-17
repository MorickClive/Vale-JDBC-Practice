package com.mc.main.ims.ui;

import com.mc.main.ims.controllers.CRUDController;
import com.mc.main.ims.controllers.CRUDInterface;
import com.mc.main.ims.controllers.ListController;
import com.mc.main.ims.controllers.NoteGroupController;
import com.mc.main.ims.dbas.PersonDBA;
import com.mc.main.ims.models.NoteGroup;
import com.mc.main.ims.models.Person;
import com.mc.main.ims.models.builders.PersonBuilder;
import com.mc.main.ims.ui.options.CRUD;
import com.mc.main.ims.ui.options.Domain;
import com.mc.main.ims.ui.options.ListCRUD;
import com.mc.main.ims.ui.tags.MainMenu;
import com.mc.main.ims.util.Console;
import com.mc.main.ims.util.ReportFormatter;

public class DomainMenu {

	private CRUDInterface<?> domain;
	private int choice;
	private boolean exitFlag, exitApplication;

	public DomainMenu() {
		super();
	}

	public void run() {
		exitFlag = false;
		exitApplication = false;

		while (!exitFlag && !exitApplication) {
			printOptions("Information Management System", Domain.class);
			choice = Console.inputInt();

			switch (choice) {
			case 1:
				domain = new CRUDController<Person>(new PersonBuilder(), new PersonDBA());
				actions(domain, Person.class);
				break;
			case 2:
				domain = new NoteGroupController();
				listActions((ListController<?>) domain, NoteGroup.class);
				break;

			case 9:
			case 0:
				exitApplication = true;
				break;

			default:
				domain = null;
				System.out.println("Input error detected, please enter a valid choice.\n");
			}
			System.out.format("%s",
					!exitFlag && !exitApplication ? ReportFormatter.div + "\n!!! Returning to Application Menu !!!\n\n" : "");
		}
	}

	private void actions(CRUDInterface<?> domain, Class<?> type) {
		boolean localFlag = false;

		while (!localFlag && !exitApplication) {
			printOptions(type.getSimpleName().toUpperCase() + " MENU:", CRUD.class);
			choice = Console.inputInt();
			
			switch (choice) {
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
			case 9:
				localFlag = true;
				break;
			case 0:
				exitApplication = true;
				break;

			default:
				System.out.println("Input error detected, please enter a valid choice.");
			}
			pressToContinue(choice > 1 && choice < 4);
		}
	}

	private void listActions(ListController<?> domain, Class<?> type) {
		boolean localFlag = false;

		while (!localFlag && !exitApplication) {
			printOptions(type.getSimpleName().toUpperCase() + " MENU:", ListCRUD.class);
			choice = Console.inputInt();

			switch (choice) {
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
				actions(domain.modify(), domain.getSubMenuType());
				break;
			case 6:
				domain.delete();
				break;
			case 9:
				localFlag = true;
				break;
			case 0:
				exitApplication = true;
				break;

			default:
				System.out.println("Input error detected, please enter a valid choice.");
			}
			pressToContinue(choice > 1 && choice < 4);
		}
	}

	private void printOptions(String header, Class<? extends Enum> menuOptions) {
		int counter = 1;
		boolean isMainMenu = menuOptions.getDeclaredAnnotation(MainMenu.class) != null;
		StringBuilder sb = new StringBuilder();
		Enum[] options = menuOptions.getEnumConstants();

		for (Enum option : options) {
			sb.append(String.format("\t%d). %s\n", counter++, option));
		}

		sb.append(String.format("%s\n\t%d). %s",
				"=".repeat(40), isMainMenu ? 0 : 9, isMainMenu ? "EXIT" : "RETURN"));
		if(!isMainMenu) { 
			sb.append(String.format("\n\t0). %s", "EXIT APP"));
		}

		ReportFormatter.reportDivided(header, sb.toString());
		System.out.println("Please enter your choice:");
	}

	private void pressToContinue(boolean flag) {
		if (flag) {
			System.out.println("!" + "Press enter to continue!");
			Console.input();
		}
	}
}
