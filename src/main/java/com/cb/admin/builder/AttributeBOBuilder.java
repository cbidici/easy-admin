package com.cb.admin.builder;

import java.lang.reflect.Type;

import com.cb.admin.bo.AttributeBO;

public class AttributeBOBuilder {

	private AttributeBO attribute;
	
	public AttributeBOBuilder() {
		this.attribute = new AttributeBO();
	}
	
	public AttributeBOBuilder type(Type type) {
		this.attribute.setType(type);
		return this;
	}
	
	public AttributeBOBuilder name(String name) {
		this.attribute.setName(name);
		return this;
	}
	
	public AttributeBO build() {
		return this.attribute;
	}
}
