package com.cb.admin.model;

import com.cb.admin.domain.Attribute;
import java.lang.reflect.Type;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AttributeResponse {
	private boolean identifier;
	private String name;
	private Type type;

	public static AttributeResponse of(Attribute attribute) {
		return AttributeResponse.builder()
				.identifier(attribute.isIdentifier())
				.name(attribute.getName())
				.type(attribute.getType())
				.build();
	}

}
