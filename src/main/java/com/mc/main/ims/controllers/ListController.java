package com.mc.main.ims.controllers;

public interface ListController<T> extends CRUDInterface<T> {
	public CRUDInterface<?> modify();
	public Class<?> getSubMenuType();
}
