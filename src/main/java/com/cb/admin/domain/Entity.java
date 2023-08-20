package com.cb.admin.domain;

import java.lang.reflect.Type;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Entity {
	private String name;
	private Type type;
	private List<Attribute> attributes;
}
