package edu.utah.autograder.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import edu.utah.autograder.network.CanvasConnection;
import edu.utah.autograder.network.PortalConnection;
import edu.utah.autograder.services.AssignmentService;
import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
public class Injection {
	
	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public PortalConnection portalConnection() {
		return new CanvasConnection();
	}
	
	@Bean
	public MultipartResolver multipartResolver() {
		return new CommonsMultipartResolver();
	}
	
	@Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
	}
	
	@Inject
	@Bean
	public AssignmentService assignmentService(PortalConnection portal) {
		return new AssignmentService(portal);
	}
	
	@Bean
	public AutograderConfiguration autograderConfiguration() {
		try {
			InputStream inputStream = findFile("configuration.yaml");
			ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
			return mapper.readValue(inputStream, AutograderConfiguration.class);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	private InputStream findFile(String path) throws FileNotFoundException {
		File f = new File(path);
		if(f.exists()) {
			return new FileInputStream(f);
		}
		
		return getClass().getClassLoader().getResourceAsStream(path);
	}
}
