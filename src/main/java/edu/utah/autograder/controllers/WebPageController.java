package edu.utah.autograder.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public abstract class WebPageController {

	public String getter(Model model) {
		model.addAttribute("module", requirejsModule());
		return templateName();
	}
	
	public abstract String requirejsModule();
	public abstract String templateName();
}
