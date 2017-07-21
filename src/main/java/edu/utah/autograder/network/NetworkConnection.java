package edu.utah.autograder.network;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.utah.autograder.config.AutograderConfiguration;

public class NetworkConnection {
	
	@Inject AutograderConfiguration configuration;
	
	private static final Logger LOGGER = Logger.getLogger(NetworkConnection.class.getName());
	public static final String LINK_REGEX = "[<](https://utah.instructure.com/api/v1/[a-zA-Z0-9/?&=_]+)[>]; rel=\"next\"";
	
	public static final String BASE_URL = "https://utah.instructure.com/api/v1/";
	protected String token = configuration.canvasToken;
	
	public byte[] downloadFile(String url) {
		HttpClient client = HttpClients.createDefault();
		HttpGet request = new HttpGet(url);
		try {
			HttpResponse response = client.execute(request);
			return EntityUtils.toByteArray(response.getEntity());
		} catch (IOException e) {
			return new byte[0];
		}
	}
	
	protected <E> E httpPostCall(String url, String contentType, HttpEntity data, Class<E> responseClass) throws IOException  {
        HttpClient client = HttpClients.createDefault();
        HttpPost request = new HttpPost(BASE_URL + url);
        configureRequest(request);
        request.addHeader("Content-type","application/json");
        String responseAsJSONString = null;
        request.setEntity(data);
        try {
        	client.execute(request);
        } catch (IOException e) {
        	LOGGER.severe(e.getMessage());
        	responseAsJSONString = String.format("{\"appError\" : \"%s\"}", e.getMessage());
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(responseAsJSONString, responseClass);
    }
	
	protected <T> T httpGetCall(String url, Class<T> responseClass) throws IOException {
		HttpClient client = HttpClients.createDefault();
		ObjectMapper mapper = new ObjectMapper();
		HttpGet request = new HttpGet(BASE_URL + url);
		configureRequest(request);
		StringBuilder sb = new StringBuilder();
		httpGetRecur(client, request, sb);
		if(sb.length() == 0) {
			sb.append("{ \"appError\" : \"Something went wrong\" }"); // make an empty object
		}
		String jsonString = sb.toString().replaceAll("\\]\\s*\\[", ",");
		return mapper.readValue(jsonString, responseClass);
	}
	
	private void httpGetRecur(HttpClient client, HttpGet request, StringBuilder responseStringBuilder) {
		HttpResponse httpResponse;
		try {
			httpResponse = client.execute(request);
			if(validResponse(httpResponse)) {
				responseStringBuilder.append(EntityUtils.toString(httpResponse.getEntity()));
				Header[] headers = httpResponse.getHeaders("Link");
				Pattern regex = Pattern.compile(LINK_REGEX);
				for(Header header : headers) {
					Matcher matcher = regex.matcher(header.getValue());
					if(matcher.find()) {
						request.setURI(new URI(matcher.group(1)));
						httpGetRecur(client, request, responseStringBuilder);
					}
				}
			} else {
				responseStringBuilder.append(buildErrorMessage(httpResponse));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	private String buildErrorMessage(HttpResponse httpResponse) {
		return String.format("{\"appError\" : \"Error code is: %d\" }", httpResponse.getStatusLine().getStatusCode());
	}

	protected <Response> Response executeRequest(HttpRequestBase request, Class<Response> responseClazz) {
		HttpClient httpClient = HttpClients.createDefault();
		try {
			HttpResponse httpResponse = httpClient.execute(request);
			validResponse(httpResponse);
			Header[] headers = httpResponse.getHeaders("Link");
			for(Header header : headers) {
				System.out.println(header.getValue());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void configureRequest(HttpRequest request) { 
		request.addHeader("Authorization", "Bearer " + token);
	}
	
	private boolean validResponse(HttpResponse response) throws ParseException, IOException {
		return response.getStatusLine().getStatusCode() == 200;
	}

}
