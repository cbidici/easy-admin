package com.cb.admin.web.dto;

import java.lang.reflect.Type;

public class AttributeDTO {
	private String key;

	private boolean identifier;

	private String field;

	private String name;

	private Type type;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public boolean isIdentifier() {
		return identifier;
	}

	public void setIdentifier(boolean identifier) {
		this.identifier = identifier;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

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
}
