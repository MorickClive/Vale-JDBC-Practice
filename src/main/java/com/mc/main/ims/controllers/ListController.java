package com.mc.main.ims.controllers;

public interface ListController<T> extends CRUDController<T> {
	public CRUDController<?> modify();
}
