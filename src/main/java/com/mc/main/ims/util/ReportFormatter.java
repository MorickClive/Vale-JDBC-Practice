package com.mc.main.ims.util;

public class ReportFormatter {
	
	public static final String div = "=".repeat(40);
	
	public static void reportHeader(String header) {
		System.out.format("%1$s%2$s\n%1$s", div + "\n", header);
	}
	
	public static void reportDivided(String header, String... contents) {
		System.out.format("%1$s%2$s\n", div + "\n", header);
		
		for(String content : contents) {
			System.out.format("%1$s%2$s\n", div + "\n", content);
		}
		
		System.out.format("%s", div + "\n");
	}
}
