package com.cb.admin.bo;

import java.lang.reflect.Type;
import java.util.Set;

public class EntityBO {

	private String name;

	private Type type;
	
	private Class cls;

	private Set<AttributeBO> attributes;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Class getCls() {
		return cls;
	}

	public void setCls(Class cls) {
		this.cls = cls;
	}

	public Set<AttributeBO> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<AttributeBO> attributes) {
		this.attributes = attributes;
	}

}
