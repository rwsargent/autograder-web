package edu.utah.autograder.services;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import edu.utah.autograder.network.PortalConnection;
import edu.utah.autograder.network.pojo.Assignment;

@Service
public class AssignmentService {

	private PortalConnection portal;
	
	@Inject
	public AssignmentService(PortalConnection portal) {
		this.portal = portal;
	}
	
	public Assignment getCurrentAssignment() {
		return new Assignment("Assignment 1", "1");
	}
}
