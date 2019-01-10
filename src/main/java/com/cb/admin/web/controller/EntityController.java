package com.cb.admin.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cb.admin.web.dto.AttributeDTO;
import com.cb.admin.web.dto.EntityDTO;
import com.cb.admin.web.service.EntityService;

@RestController
@RequestMapping("/easy-admin")
public class EntityController {

	@Autowired
	private EntityService entityService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("entities")
	public ResponseEntity<?> entities() {
		List<EntityDTO> resultList = entityService.getEntities().values().stream()
				.map(entityBO -> modelMapper.map(entityBO, EntityDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<List<?>>(resultList, HttpStatus.OK);
	}
	
	@GetMapping("entities/{key}/attributes")
	public ResponseEntity<?> entityAttributes(@PathVariable String key) {
		List<AttributeDTO> resultList = entityService.getEntity(key).getAttributes().stream()
				.map(attrbiuteBO -> modelMapper.map(attrbiuteBO, AttributeDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<List<?>>(resultList, HttpStatus.OK);
	}

	@GetMapping("entities/{key}/data")
	public ResponseEntity<?> getEntity(@PathVariable String key) {
		return new ResponseEntity<List<?>>(entityService.queryEntities(key), HttpStatus.OK);
	}

}
