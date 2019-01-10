package com.cb.admin.builder;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.cb.admin.bo.AttributeBO;
import com.cb.admin.bo.EntityBO;

public class EntityBOBuilder {

	private EntityBO entity;
	
	public EntityBOBuilder() {
		this.entity = new EntityBO();
		this.entity.setAttributes(new ArrayList<>());
	}
	
	public EntityBOBuilder identifier(String identifier) {
		this.entity.setIdentifier(identifier);
		return this;
	}
	
	public EntityBOBuilder type(Type type) {
		this.entity.setType(type);
		return this;
	}
	
	public EntityBOBuilder name(String name) {
		this.entity.setName(name);
		return this;
	}
	
	public EntityBOBuilder category(String category) {
		this.entity.setCategory(category);
		return this;
	}
	
	public EntityBOBuilder cls(Class<?> cls) {
		this.entity.setCls(cls);
		return this;
	}
	
	public EntityBOBuilder attributes(List<AttributeBO> attributes) {
		this.entity.setAttributes(attributes);
		return this;
	}
	
	public EntityBO build() {
		this.entity.setKey(UUID.randomUUID().toString());
		return this.entity;
	}
}
