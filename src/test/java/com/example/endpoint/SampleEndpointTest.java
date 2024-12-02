package com.example.endpoint;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.example.demo.DemoApplication;
import com.example.mapper.CourseMapper;
import com.example.mapper.StudentMapper;
import com.example.model.CourseDTO;
import com.example.repo.CourseRepo;
import com.example.repo.StudentRepo;
import com.example.service.SampleService;
import com.example.service.impl.SampleServiceImpl;

@ContextConfiguration(classes = { DemoApplication.class, SampleEndpoint.class, SampleServiceImpl.class,
		StudentRepo.class, CourseRepo.class })
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
@TestPropertySource(properties = { "spring.jpa.hibernate.ddl-auto=create-drop" })
public class SampleEndpointTest {
	@Autowired
	MockMvc mckMvc;

	@Autowired
	SampleEndpoint smplendpt;

	@Autowired
	SampleService sampleService;

	@Autowired
	CourseRepo courseRepo;

	@Autowired
	StudentRepo stdntRepo;
	
	@Autowired
	StudentMapper stdntMppr;
	
	@Autowired
	CourseMapper  courseMappr;

	@Test
	public void test1() throws Exception {
		this.mckMvc.perform(MockMvcRequestBuilders.get("/testFakeStoreAPI"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		assertNotNull(smplendpt.testDownStreamAPI());
	}

	@Test
	public void test2() throws Exception {
		this.mckMvc
				.perform(MockMvcRequestBuilders.post("/addStudent").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content("{\r\n" + "    \"name\": \"test\",\r\n" + "    \"courses\": [\r\n" + "        {\r\n"
								+ "            \"name\":\"TestCourse\"\r\n" + "        }\r\n" + "    ]\r\n" + "}"))
				.andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"name\":\"test\",\"courses\":null}"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
	}

	@Test
	public void test3() throws Exception {
		this.mckMvc.perform(MockMvcRequestBuilders.get("/getStudent/1"))
				.andExpect(MockMvcResultMatchers.content()
						.string("{\"id\":1,\"name\":\"test\",\"courses\":[{\"name\":\"TestCourse\",\"id\":1}]}"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		this.mckMvc.perform(MockMvcRequestBuilders.get("/getStudent/2"))
				.andExpect(MockMvcResultMatchers.status().is5xxServerError()).andReturn();
	}

	@Test
	public void test4() throws Exception {
		this.mckMvc
				.perform(MockMvcRequestBuilders.patch("/updateStudent/1").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content("{\r\n" + "    \"id\":1,\r\n" + "    \"name\": \"test2\",\r\n"
								+ "    \"courses\": [\r\n" + "        {\r\n" + "            \"name\":\"TestCourse\"\r\n"
								+ "        }\r\n" + "    ]\r\n" + "}"))

				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

		this.mckMvc
				.perform(MockMvcRequestBuilders.patch("/updateStudent/2").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content("{\r\n" + "    \"id\":1,\r\n" + "    \"name\": \"test2\",\r\n"
								+ "    \"courses\": [\r\n" + "        {\r\n" + "            \"name\":\"TestCourse\"\r\n"
								+ "        }\r\n" + "    ]\r\n" + "}"))

				.andExpect(MockMvcResultMatchers.status().is5xxServerError()).andReturn();

	}

	@Test
	public void test5() throws Exception {
		this.mckMvc
				.perform(MockMvcRequestBuilders.patch("/updateCourse/1/course/1")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content("{\r\n" + "    \"newCourseName\": \"testCourse2\"\r\n" + "}"))
				.andExpect(MockMvcResultMatchers.content().string("RecordUpdated"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

		this.mckMvc
				.perform(MockMvcRequestBuilders.patch("/updateCourse/1/course/2")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content("{\r\n" + "    \"newCourseName\": \"testCourse2\"\r\n" + "}"))
				.andExpect(MockMvcResultMatchers.content().string("Record with provided course Id :{" + "2" + "} not found"))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError()).andReturn();

	}

	@Test
	public void test6() throws Exception {
		this.mckMvc.perform(MockMvcRequestBuilders.delete("/deleteStudent/1"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		this.mckMvc.perform(MockMvcRequestBuilders.delete("/deleteStudent/2"))
				.andExpect(MockMvcResultMatchers.status().is5xxServerError()).andReturn();
	}

	@Test
	public void test7() {
		assertNull(stdntMppr.mapStudentEntityFromStudentDto(null));		
		assertNull(courseMappr.mapCourseEntityFromCourseDto(new CourseDTO()).getName());
	}
	
}
