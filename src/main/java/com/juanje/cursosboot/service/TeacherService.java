package com.juanje.cursosboot.service;

import com.juanje.cursosboot.domain.Course;
import com.juanje.cursosboot.domain.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeacherService {

	Page<Teacher> findTeachers(String name, Pageable pageable);

	Teacher get(Long id);

	Teacher add(Teacher teacher);

	Page<Course> findCourses(Long teacherId, String name, Pageable pageable);

	Course addCourse(Long id, Course course);
}
