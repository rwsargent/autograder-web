package edu.utah.autograder.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.utah.autograder.entities.Submission;

@Repository
public interface SubmissionRepository extends CrudRepository<Submission, UUID> {

	List<Submission> findAll();
	
	List<Submission> findByStudentId(String studentId);
	
	List<Submission> findByAssignmentId(String assignmentId);
}
