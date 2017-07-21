package edu.utah.autograder.assignments;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.utah.autograder.controllers.WebPageController;
import edu.utah.autograder.network.pojo.Assignment;
import edu.utah.autograder.services.AssignmentService;

/**
 * Logic for all routes starting with /assignments
 * @author ryans
 */
@Controller
@RequestMapping("/assignments")
public class AssignmentController extends WebPageController {
	private AssignmentService assignmentServce;

	@Inject
	public AssignmentController(AssignmentService assignmentService) {
		this.assignmentServce = assignmentService;
	}
	
	@Override
	@RequestMapping(method = RequestMethod.GET)
	public String getter(Model model) {
		Assignment currentAssignment = assignmentServce.getCurrentAssignment();
		model.addAttribute("assignment", currentAssignment);
		model.addAttribute("results", java.util.Collections.emptyList());
		return super.getter(model);
	}
	
	@Override
	public String requirejsModule() {
		return "js/modules/assignment.js";
	}

	@Override
	public String templateName() {
		return "assignments";
	}
}