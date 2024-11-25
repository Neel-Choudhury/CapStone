package com.example.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.entity.Student;

public interface StudentRepo extends CrudRepository<Student, Long>{
	
}

