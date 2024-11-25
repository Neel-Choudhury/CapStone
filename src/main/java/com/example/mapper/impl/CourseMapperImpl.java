package com.example.mapper.impl;

import org.springframework.stereotype.Service;

import com.example.entity.Course;
import com.example.mapper.CourseMapper;
import com.example.model.CourseDTO;

@Service
public class CourseMapperImpl implements CourseMapper{

	@Override
	public Course mapCourseEntityFromCourseDto(CourseDTO suppliedDto) {
		 Course course = new Course();
		 course.setName(suppliedDto.getName());
		 return course;
	}

}
