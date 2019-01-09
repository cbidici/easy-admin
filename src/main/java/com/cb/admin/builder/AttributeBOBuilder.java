package com.cb.admin.builder;

import java.lang.reflect.Type;
import java.util.UUID;

import com.cb.admin.bo.AttributeBO;

public class AttributeBOBuilder {

	private AttributeBO attribute;
	
	public AttributeBOBuilder() {
		this.attribute = new AttributeBO();
	}
	
	public AttributeBOBuilder identifier(String identifier) {
		this.attribute.setIdentifier(identifier);
		return this;
	}
	
	public AttributeBOBuilder name(String name) {
		this.attribute.setName(name);
		return this;
	}
	
	public AttributeBOBuilder type(Type type) {
		this.attribute.setType(type);
		return this;
	}
	
	public AttributeBO build() {
		this.attribute.setKey(UUID.randomUUID().toString());
		return this.attribute;
	}
}
