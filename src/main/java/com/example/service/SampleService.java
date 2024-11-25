package com.example.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.entity.Student;
import com.example.model.EndpointAPIResponse;
import com.example.model.FakeStoreProductDownstream;
import com.example.model.StudentDTO;

public interface SampleService {
	Student addStudent(StudentDTO student)throws Exception;
	StudentDTO updateStudent(Long id, StudentDTO student)throws Exception;
	ResponseEntity<String> updateCourse(Long Studentid, Long courseId, String updatedName)throws Exception;
	void deleteStudent(long studentId)throws Exception;
	StudentDTO getStudent(long studentId)throws Exception;
	ResponseEntity<List<EndpointAPIResponse>> getDownStreamResponse() throws Exception;
}
