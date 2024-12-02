package com.example.util;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class PacketInspectorTest {

	@InjectMocks
	private PacketInspector packetInspector;

	@Mock
	private RestTemplate restTemplate;

	@Mock
	private ResponseEntity<String> responseEntity;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		// Setting the mock downstream URL value (you can mock this if needed)
		ReflectionTestUtils.setField(packetInspector, "dwnstream", "https://fakestoreapi.com/products");
	}

	@Test
	public void testInspectResponsePacketCandidate_SuccessfulResponse() {
		// Arrange
		String responseBody = "Valid response body";
		ResponseEntity<String> responseEntity = new ResponseEntity<>(responseBody, HttpStatus.OK);
		when(restTemplate.exchange(eq("https://fakestoreapi.com/products"), eq(HttpMethod.GET), any(), eq(String.class)))
				.thenReturn(responseEntity);

		// Act
		String result = packetInspector.inspectResponsePacketCandidate();

		// Assert
		assertNotNull(result);
		assertEquals(responseBody, result);
	}

	@Test
	public void testInspectResponsePacketCandidate_EmptyBody() {
		// Arrange
		String responseBody = "";
		ResponseEntity<String> responseEntity = new ResponseEntity<>(responseBody, HttpStatus.OK);
		when(restTemplate.exchange(eq("http://downstream.endpoint"), eq(HttpMethod.GET), any(), eq(String.class)))
				.thenReturn(responseEntity);

		// Act
		String result = packetInspector.inspectResponsePacketCandidate();

		// Assert
		assertNull(result); // As empty body should throw exception internally, no result should be returned
	}

	@Test
	public void testInspectResponsePacketCandidate_Non200Response() {
		// Arrange
		ResponseEntity<String> responseEntity = new ResponseEntity<>("Some error", HttpStatus.BAD_REQUEST);
		when(restTemplate.exchange(eq("https://fakestoreapi.com/products"), eq(HttpMethod.GET), any(), eq(String.class)))
				.thenReturn(responseEntity);

		// Act
		String result = packetInspector.inspectResponsePacketCandidate();

		// Assert
		assertNull(result); // As the status code is not 200, it should return null
	}

	@Test
	public void testInspectResponsePacketCandidate_ResponseEntityNull() {
		// Arrange
		when(restTemplate.exchange(eq("https://fakestoreapi.com/products"), eq(HttpMethod.GET), any(), eq(String.class)))
				.thenReturn(null);

		// Act
		String result = packetInspector.inspectResponsePacketCandidate();

		// Assert
		assertNull(result); // Since ResponseEntity is null, it should return null
	}

	@Test
	public void testInspectResponsePacketCandidate_ExceptionHandling() {
		// Arrange
		when(restTemplate.exchange(eq("https://fakestoreapi.com/products"), eq(HttpMethod.GET), any(), eq(String.class)))
				.thenThrow(new RuntimeException("Simulated exception"));

		// Act
		String result = packetInspector.inspectResponsePacketCandidate();

		// Assert
		assertNull(result); // The exception should be caught and result should be null
	}
}
