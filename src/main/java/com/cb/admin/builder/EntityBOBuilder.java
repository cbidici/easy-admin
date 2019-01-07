package com.cb.admin.builder;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.cb.admin.bo.AttributeBO;
import com.cb.admin.bo.EntityBO;

public class EntityBOBuilder {

	private EntityBO entity;
	
	public EntityBOBuilder() {
		this.entity = new EntityBO();
		this.entity.setAttributes(new HashSet<>());
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
	
	public EntityBOBuilder attributes(Set<AttributeBO> attributes) {
		this.entity.setAttributes(attributes);
		return this;
	}
	
	public EntityBO build() {
		this.entity.setKey(UUID.randomUUID().toString());
		return this.entity;
	}
}
