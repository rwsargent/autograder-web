package edu.utah.autograder.assignments;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.jar.JarInputStream;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import edu.utah.autograder.network.pojo.FileResponse;

@Controller
public class SubmissionUploadController {
	
	@Inject
	public SubmissionUploadController() {
		
	}
	
	@PostMapping("/assignment/submit")
	@ResponseBody
	public FileResponse handeFileUpload(@RequestParam("file") MultipartFile file) {
		JarInputStream jarInputStream = null;
		FileResponse fileResponse = new FileResponse();
		try {
			jarInputStream = new JarInputStream(new ByteArrayInputStream(file.getBytes()));
			fileResponse.message = "Success!";
		} catch (IOException e) {
			// this is in goddamn memory, there is no IO. 
			fileResponse.message = e.getMessage();
		}
		return fileResponse;
	}
}
