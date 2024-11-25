package com.example.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Course;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface CourseRepo extends CrudRepository<Course, Long> {
	@Modifying
	@Query(value = "UPDATE scalerbatchprojectdb.course SET course.name=:updateCourseName where course.student =:studentId AND course.id=:courseId", nativeQuery = true)
	int updateCourse(@Param("studentId") Long studentId, @Param("courseId") Long courseId, String updateCourseName);
}
