package com.cb.admin.builder;

import java.lang.reflect.Type;

import com.cb.admin.bo.EntityBO;

public class EntityBOBuilder {

	private EntityBO entity;
	
	public EntityBOBuilder() {
		this.entity = new EntityBO();
	}
	
	public EntityBOBuilder type(Type type) {
		this.entity.setType(type);
		return this;
	}
	
	public EntityBOBuilder name(String name) {
		this.entity.setName(name);
		return this;
	}
	
	public EntityBO build() {
		return this.entity;
	}
}
