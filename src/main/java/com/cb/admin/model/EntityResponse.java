package com.cb.admin.model;

import com.cb.admin.domain.Entity;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EntityResponse {
	private String name;
	private String type;
	private List<AttributeResponse> attributes;

	public static EntityResponse of(Entity entity) {
		return EntityResponse.builder()
				.name(entity.getName())
				.type(entity.getType().getTypeName())
				.attributes(entity.getAttributes().stream().map(AttributeResponse::of).toList())
				.build();
	}
}
