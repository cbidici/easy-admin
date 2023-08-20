package com.cb.admin.controller;

import com.cb.admin.service.EntityService;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@DeleteMapping("entities/{key}/data/{identifiers}")
	public ResponseEntity<Void> deleteEntityData(@PathVariable String key, @PathVariable List<String> identifiers) {
		entityService.deleteEntities(key, identifiers);
		return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
