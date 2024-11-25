package com.example.mapper;

import org.mapstruct.Mapper;

import com.example.entity.Student;
import com.example.model.StudentDTO;

@Mapper
public interface StudentMapper {
	Student mapStudentEntityFromStudentDto(StudentDTO suppliedDto);
	StudentDTO mapStudentDtoFromStudentEntity(Student suppliedEntity);
}

