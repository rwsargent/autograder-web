package edu.utah.autograder;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.utah.autograder.entities.Submission;
import edu.utah.autograder.network.PortalConnection;
import edu.utah.autograder.repositories.SubmissionRepository;
import sun.security.provider.SecureRandom;

@Controller
public class LoginController {
	
	private PortalConnection portalConnection;
	private SubmissionRepository submissionRepository;

	@Inject
	public LoginController(PortalConnection canvasConnection, SubmissionRepository submissionRepository) {
		this.portalConnection = canvasConnection;
		this.submissionRepository = submissionRepository;
	}
	
	@RequestMapping("/login")
	public String loggingInWhat(Model model) {
		model.addAttribute("name", portalConnection.getStudents().getName());
		byte[] contents = new byte[256];
		new SecureRandom().engineNextBytes(contents);
		Submission submission = new Submission("", contents, "", "");
		submissionRepository.save(submission);
		return "login";
	}
}
