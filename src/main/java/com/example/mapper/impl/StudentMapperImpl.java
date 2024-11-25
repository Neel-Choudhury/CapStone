package com.example.mapper.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Course;
import com.example.entity.Student;
import com.example.mapper.StudentMapper;
import com.example.model.CourseDTO;
import com.example.model.StudentDTO;
import com.example.repo.CourseRepo;
import com.example.repo.StudentRepo;

@Service
public class StudentMapperImpl implements StudentMapper {
	@Autowired
	StudentRepo stdntRepo;

	@Autowired
	CourseRepo courseRepo;

	@Override
	public Student mapStudentEntityFromStudentDto(StudentDTO suppliedDto) {
		if (suppliedDto == null) {
			return null;
		} else {
			Student student = new Student();
			student.setName(suppliedDto.getName());
			final Student refstdnt = stdntRepo.save(student);
			Set<Course> courses = new HashSet<>();
			suppliedDto.getCourses().forEach(e -> {
				Course courseDto = new Course();
				courseDto.setName(e.getName());
				courseDto.setStudents(refstdnt);
				courses.add(courseDto);
				courseRepo.save(courseDto);
			});
			return student;
		}
	}

	@Override
	public StudentDTO mapStudentDtoFromStudentEntity(Student suppliedEntity) {
		StudentDTO dto = new StudentDTO();
		dto.setId(suppliedEntity.getId());
		dto.setName(suppliedEntity.getName());
		Set<CourseDTO> courses = new HashSet<>();
		suppliedEntity.getCourses().forEach(e->{ 
			CourseDTO courseDto = new CourseDTO();
			courseDto.setId(e.getId());
			courseDto.setName(e.getName());
			courses.add(courseDto);
		});
		dto.setCourses(courses);
		return dto;
	}

}
