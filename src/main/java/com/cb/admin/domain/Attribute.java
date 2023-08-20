package com.cb.admin.domain;

import java.lang.reflect.Type;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Attribute {
	private boolean identifier;
	private String name;
	private Type type;
}
