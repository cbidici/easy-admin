package com.cb.admin.web.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cb.admin.bo.EntityBO;
import com.cb.admin.web.dto.ResultDTO;
import com.cb.admin.web.service.EntityService;

@RestController
@RequestMapping("/cb-admin")
public class MainController {

	@Autowired
	private EntityService entityService;
	
	@GetMapping
	public ResultDTO<?> main(Model model) {
		ResultDTO<Set<EntityBO>> result = new ResultDTO<>();
		result.setContent(entityService.getEntities());
		model.addAttribute("result", result);
		return result;
	}
	
}
