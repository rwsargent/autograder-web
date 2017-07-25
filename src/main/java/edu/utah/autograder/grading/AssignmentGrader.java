package edu.utah.autograder.grading;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import autograderutils.AutograderResult;
import autograderutils.JUnitPlugin;

public class AssignmentGrader {
	
	public AutograderResult grade(File submittedJar, Class<?> graderClass) throws MalformedURLException {
		URLClassLoader classLoader = createGraderClassLoader(submittedJar);
		JUnitPlugin graderPlugin = getJunitPlugin(classLoader);
		return graderPlugin.grade(graderClass);
	}

	private JUnitPlugin getJunitPlugin(URLClassLoader classLoader) {
		Class<?> clazz = null;
		JUnitPlugin graderPlugin = null;
		try {
			clazz = classLoader.loadClass(JUnitPlugin.class.getName());
			graderPlugin = (JUnitPlugin) clazz.newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			throw new IllegalStateException("This is unexpected, the grader plugin should be included on the class path.");
		}
		return graderPlugin;
	}
	
	private URLClassLoader createGraderClassLoader(File file) throws MalformedURLException {
		URL url = file.toURI().toURL();
		URLClassLoader loader = new URLClassLoader(new URL[]{url}, getClass().getClassLoader());
		return loader;
	}
}
