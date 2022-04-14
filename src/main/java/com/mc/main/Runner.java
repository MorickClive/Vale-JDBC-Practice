package com.mc.main;

import com.mc.main.ims.IMS;

public class Runner {
	
	public static void main(String[] args) {
		try(IMS ims = new IMS()) {
			ims.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
