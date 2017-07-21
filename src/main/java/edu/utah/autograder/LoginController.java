package edu.utah.autograder;

import java.sql.Date;
import java.time.Instant;

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
		Submission submission = new Submission();
		submission.setAssignmentId("assignmentid");
		byte[] contents = new byte[256];
		new SecureRandom().engineNextBytes(contents);
		submission.setFileContents(contents);
		submission.setFileName("File Name");
		submission.setStudentId("studentId");
		submission.setUploadTime(Date.from(Instant.now()));
		submissionRepository.save(submission);
		return "login";
	}
}
