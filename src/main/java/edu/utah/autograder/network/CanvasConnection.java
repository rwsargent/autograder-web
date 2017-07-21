package edu.utah.autograder.network;

import edu.utah.autograder.network.pojo.Student;

public class CanvasConnection implements PortalConnection{

	@Override
	public Student getStudents() {
		return new Student();
	}
}
