package com.mc.main.ims.util;

import java.util.Scanner;

public class Console {
		private static Scanner input;
		
		private Console() { super(); }
		
		private static boolean hasScanner() {
			return input != null;
		}
		
		public static Scanner getScanner() {
			input = !hasScanner() ? new Scanner(System.in) : input;
			
			return input;
		}

		public static String input() {
			return hasScanner() ? input.nextLine() : getScanner().nextLine();
		}
		
		public static int inputInt() {
			int result = 0;
			boolean flag = true;
			
			for(int x = 0; x < 3; x++) {
				try {
					result = Integer.parseInt(Console.input());
				} catch (Exception e) {
					System.out.format("!!! Error detected, value not numeric !!!\n%s\n", 2-x + " tries left before default.");
					continue;
				}
				break;
			}
			
			return result;
		}
		
		public static int targetID() {
			System.out.println("Please enter the id:");
			return inputInt();
		}
		
		public static void close() {
			if( hasScanner() ) {
				input.close();
			}
			System.out.println("\t[RESOURCE]: Scanner closed!");
		}
}
