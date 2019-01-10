package com.cb.admin.bo;

import java.lang.reflect.Type;
import java.util.List;

public class EntityBO {
	
	private String key;
	
	private String identifier;
	
	private String name;
	
	private String  category;

	private Type type;
	
	private Class<?> cls;

	private List<AttributeBO> attributes;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Class<?> getCls() {
		return cls;
	}

	public void setCls(Class<?> cls) {
		this.cls = cls;
	}

	public List<AttributeBO> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<AttributeBO> attributes) {
		this.attributes = attributes;
	}

}
