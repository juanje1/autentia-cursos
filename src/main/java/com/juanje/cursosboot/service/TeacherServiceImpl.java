package com.juanje.cursosboot.service;

import com.juanje.cursosboot.data.CourseRepository;
import com.juanje.cursosboot.domain.Course;
import com.juanje.cursosboot.data.TeacherRepository;
import com.juanje.cursosboot.domain.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@Component("teacherService")
@Transactional
class TeacherServiceImpl implements TeacherService {

	private final TeacherRepository teacherRepository;

	private final CourseRepository courseRepository;

	TeacherServiceImpl(TeacherRepository teacherRepository, CourseRepository courseRepository) {
		this.teacherRepository = teacherRepository;
		this.courseRepository = courseRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Teacher> findTeachers(String name, Pageable pageable) {
		Page<Teacher> teachers;
		if (StringUtils.hasLength(name)) {
			teachers = teacherRepository.findByNameContainingAllIgnoringCase(name.trim(), pageable);
		}else{
			teachers =  teacherRepository.findAll(pageable);
		}
		return teachers;
	}

	@Override
	@Transactional(readOnly = true)
	public Teacher get(Long id) {
		Assert.notNull(id, "Id must not be null");
		return teacherRepository.findById(id);
	}

	@Override
	public Teacher add(Teacher teacher) {
		return teacherRepository.save(teacher);
	}

	@Override
	public Page<Course> findCourses(Long teacherId, String name, Pageable pageable) {
		Page<Course> courses;
		if (StringUtils.hasLength(name)) {
			courses = courseRepository.findByTeacherIdAndNameContainingAllIgnoringCase(teacherId, name.trim(), pageable);
		}else{
			courses =  courseRepository.findByTeacherId(teacherId, pageable);
		}
		return courses;
	}

	@Override
	public Course addCourse(Long id, Course course) {
		course.setTeacher(get(id));
		return courseRepository.save(course);
	}

}
