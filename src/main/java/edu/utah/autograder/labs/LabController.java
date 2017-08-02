package edu.utah.autograder.labs;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.utah.autograder.controllers.WebPageController;

@Controller
@RequestMapping("/labs")
public class LabController extends WebPageController {
	
	
	@Override
	@RequestMapping(method = RequestMethod.GET)
	public String getter(Model model) {
		return super.getter(model);
	}

	@Override
	public String requirejsModule() {
		return "js/modules/lab.js";
	}

	@Override
	public String templateName() {
		// TODO Auto-generated method stub
		return "lab";
	}

}
