package com.example.mapper;

import org.mapstruct.Mapper;

import com.example.entity.Course;
import com.example.model.CourseDTO;

@Mapper
public interface CourseMapper {
	Course mapCourseEntityFromCourseDto(CourseDTO suppliedDto);
}
