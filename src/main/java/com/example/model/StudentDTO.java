package com.example.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTO {
	private Long id;
	private String name; 
	Set<CourseDTO> courses = new HashSet<>();
}