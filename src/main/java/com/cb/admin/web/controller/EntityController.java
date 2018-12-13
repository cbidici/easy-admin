package com.cb.admin.web.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cb.admin.web.service.EntityService;

@RestController
@RequestMapping("/cb-admin")
public class EntityController {

	@Autowired
	private EntityService entityService;
	
	@GetMapping("entities")
	public ResponseEntity<?> main(Model model) {
		return new ResponseEntity<Set<?>>(entityService.getEntities(), HttpStatus.OK);
	}
	
}
