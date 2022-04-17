package com.mc.main.ims;

import com.mc.main.ims.ui.DomainMenu;
import com.mc.main.ims.util.Console;
import com.mc.main.ims.util.DatabaseConnection;

public class IMS implements AutoCloseable {
	
	public void start() {
		DomainMenu menu = new DomainMenu();
		
		loadResources();
		menu.run();
		
		System.out.println("\nExiting Application...");
	}

	private void loadResources() {
		DatabaseConnection.runSchema("Schema-Person.sql");
		DatabaseConnection.runSchema("Schema-NoteTracker.sql");
	}	
	
	@Override
	public void close() throws Exception {
		DatabaseConnection.close();
		Console.close();
	}

}
