package edu.utah.autograder.entities;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import autograderutils.AutograderResult;

@Entity
public class Submission {
	
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	@Column(unique = true, nullable = false)
	@Type(type="pg-uuid")
	private UUID id;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date uploadtime;
	
	@Column(nullable = false)
	private byte[] fileContents;
	
	@Column(nullable = false)
	private String fileName;
	
	@Column(nullable = false)
	private String studentId;
	
	@Column(nullable = false)
	private String assignmentId;
	
	@Column
	private int score;
	
	@Column
	private int total;
	
	@Column
	private String graderOutput;
	
	public Submission(String filename, byte[] fileContents, String studentId, String assignmentId) {
		this.fileName = filename;
		this.fileContents = fileContents;
		this.studentId = studentId;
		this.assignmentId = assignmentId;
		this.uploadtime = Date.from(Instant.now());
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Date getUploadTime() {
		return uploadtime;
	}

	public void setUploadTime(Date uploadtime) {
		this.uploadtime = uploadtime;
	}

	public byte[] getFileContents() {
		return fileContents;
	}

	public void setFileContents(byte[] fileContents) {
		this.fileContents = fileContents;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(String assignmentId) {
		this.assignmentId = assignmentId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getGraderOutput() {
		return graderOutput;
	}

	public void setGraderOutput(String graderOutput) {
		this.graderOutput = graderOutput;
	}
	
	public void addResult(AutograderResult result) {
		
	}
}
