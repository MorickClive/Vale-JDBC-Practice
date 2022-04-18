package com.mc.main.ims.controllers;

public interface CRUDInterface<T> {

	public void create();
	public void read();
	public void readAll();
	public void update();
	public void delete();
}
