package com.example.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Student;
import com.example.model.CourseDTO3;
import com.example.model.EndpointAPIResponse;
import com.example.model.StudentDTO;
import com.example.service.SampleService;

@RestController
public class SampleEndpoint {

	@Autowired
	SampleService sampleSvc;

	@PostMapping("/addStudent")
	ResponseEntity<Student> createStudent(@RequestBody StudentDTO stdntDto) throws Exception {
		return ResponseEntity.ok().body(sampleSvc.addStudent(stdntDto));
	}

	@GetMapping("/getStudent/{id}")
	public ResponseEntity<StudentDTO> getStudent(@PathVariable("id") Long id) throws Exception {
		return ResponseEntity.ok().body(sampleSvc.getStudent(id));
	}

	@PatchMapping("/updateStudent/{id}")
	public ResponseEntity<StudentDTO> updateStudent(@PathVariable("id") Long id, @RequestBody StudentDTO stdntDto)
			throws Exception {
		return ResponseEntity.ok().body(sampleSvc.updateStudent(id, stdntDto));
	}

	@PatchMapping("/updateCourse/{Studentid}/course/{courseId}")
	public ResponseEntity<String> updateCourse(@PathVariable("Studentid") Long Studentid,
			@PathVariable("courseId") Long courseId, @RequestBody CourseDTO3 courseDto) throws Exception {
		return sampleSvc.updateCourse(Studentid, courseId, courseDto.getNewCourseName());
	}

	@DeleteMapping("/deleteStudent/{id}")
	public void deleteStudent(@PathVariable("id") Long id) throws Exception {
		sampleSvc.deleteStudent(id);
	}

	@GetMapping("/testFakeStoreAPI")
	public ResponseEntity<List<EndpointAPIResponse>> testDownStreamAPI() throws Exception {
		return sampleSvc.getDownStreamResponse();
	}

}
