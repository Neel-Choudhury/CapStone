package com.example.endpoint;

import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(classes = {
		ServletWebServerFactoryAutoConfiguration.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
				"spring.cloud.config.enabled=false" })
@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(SampleEndpoint.class)
public class SampleEndpointTest {
	@Autowired
	MockMvc mckMvc;

	@Autowired
	SampleEndpoint smplendpt;

	@Test
	public void test1() throws Exception {

		this.mckMvc.perform(MockMvcRequestBuilders.get("/testFakeStoreAPI"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		assertNull(smplendpt.testDownStreamAPI());
	}
}
