package com.cb.admin.web.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cb.admin.web.service.EntityService;

@RestController
@RequestMapping("/cb-admin")
public class EntityController {

	@Autowired
	private EntityService entityService;
	
	@GetMapping("entities")
	public ResponseEntity<?> main() {
		return new ResponseEntity<Set<?>>(entityService.getEntities(), HttpStatus.OK);
	}
	
	@GetMapping("entities/{entity}")
	public ResponseEntity<?> getEntity(@PathVariable String entity) {
		return new ResponseEntity<List<?>>(entityService.queryEntities(entity), HttpStatus.OK);
	}
	
}
