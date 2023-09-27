package com.cb.admin.controller;

import com.cb.admin.service.EntityService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cb.admin.model.EntityResponse;

@RestController
@RequestMapping("/easy-admin")
@RequiredArgsConstructor
public class EntityController {

	private final EntityService entityService;

	@GetMapping("entities")
	public ResponseEntity<?> entities() {
		List<EntityResponse> resultList = entityService.getEntities().values().stream()
				.map(EntityResponse::of)
				.collect(Collectors.toList());
		return new ResponseEntity<List<?>>(resultList, HttpStatus.OK);
	}

	@GetMapping("entities/{name}/data")
	public ResponseEntity<List<?>> getEntity(@PathVariable String name) {
		return new ResponseEntity<>(entityService.queryEntities(name), HttpStatus.OK);
	}

	@PostMapping("entities/{name}/data")
	public ResponseEntity<Void> createEntity(@PathVariable String name, @RequestBody String request)
			throws ClassNotFoundException, JsonProcessingException {
		Object obj = new ObjectMapper().registerModule(new JavaTimeModule()).readValue(request, Class.forName(entityService.getEntity(name).getType().getTypeName()));
		entityService.saveEntity(obj);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("entities/{key}/data/{identifier}")
	public ResponseEntity<Void> deleteEntityData(@PathVariable String key, @PathVariable String identifier) {
		entityService.deleteEntity(key, identifier);
		return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
