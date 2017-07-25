package edu.utah.autograder.assignments;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.UUID;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import autograderutils.AutograderResult;
import edu.utah.autograder.entities.Submission;
import edu.utah.autograder.grading.AssignmentGrader;
import edu.utah.autograder.network.pojo.FileResponse;
import edu.utah.autograder.repositories.SubmissionRepository;

@Controller
public class SubmissionUploadController {
	
	private Log logger = LogFactory.getLog(SubmissionUploadController.class);
	private AssignmentGrader assignmentGrader;
	private AssignmentCache assignmentCache;
	private SubmissionRepository submissionRepository;
	
	@Inject
	public SubmissionUploadController(AssignmentGrader assignmentGrader, AssignmentCache cache, SubmissionRepository submissionRepository) {
		this.assignmentGrader = assignmentGrader;
		this.assignmentCache = cache;
		this.submissionRepository = submissionRepository;
	}
	
	@PostMapping("/assignment/submit")
	@ResponseBody
	public FileResponse handeFileUpload(@RequestParam("file") MultipartFile file) {
		FileResponse fileResponse = new FileResponse();
		Path tempSubmissionPath = null;
		try {
			String filename = getFileName();
			
			tempSubmissionPath = Files.createTempFile(FilenameUtils.getBaseName(filename), ".jar", (FileAttribute<?>[])null);
			Files.write(tempSubmissionPath, file.getBytes(), (OpenOption[])null);
			
			AutograderResult result = assignmentGrader.grade(tempSubmissionPath.toFile(), assignmentCache.getCurrentAssignmentGraderClass());
			
			Submission submission = new Submission(file.getName(), file.getBytes(), "non-id", "testAssignment");
			submission.addResult(result);
			submission = submissionRepository.save(submission);
			
			fileResponse.score = result.getScore();
			fileResponse.total = result.getTotal();
		} catch (IOException e) {
			fileResponse.message = e.getMessage();
		} finally {
			if(tempSubmissionPath != null) {
				try {
					FileUtils.forceDelete(tempSubmissionPath.toFile());
				} catch (IOException e) {
					logger.warn(e);
				}
			}
		}
		return fileResponse;
	}

	private String getFileName() {
		return UUID.randomUUID().toString();
	}
}
