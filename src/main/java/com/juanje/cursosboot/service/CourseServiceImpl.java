package com.juanje.cursosboot.service;

import com.juanje.cursosboot.data.CourseRepository;
import com.juanje.cursosboot.data.SubjectRepository;
import com.juanje.cursosboot.domain.Course;
import com.juanje.cursosboot.domain.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@Component("courseService")
@Transactional
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;

    private final SubjectRepository subjectRepository;

    public CourseServiceImpl(CourseRepository courseRepository, SubjectRepository subjectRepository){
        this.courseRepository = courseRepository;
        this.subjectRepository = subjectRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public Page<Course> findCourses(String name, Pageable pageable) {
        Page<Course> courses;
        if(StringUtils.hasLength(name)){
            courses = courseRepository.findByNameContainingAllIgnoringCase(name.trim(), pageable);
        }else{
            courses = courseRepository.findAll(pageable);
        }
        return courses;
    }

    @Override
    @Transactional(readOnly = true)
    public Course get(Long id) {
        Assert.notNull(id, "Id must not be null");
        return courseRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Subject> findSubjects(Long courseId, String name, Pageable pageable) {
        Assert.notNull(courseId, "Course must not be null");
        Page<Subject> subjects;
        if(StringUtils.hasLength(name)){
            subjects = subjectRepository.findByCourseIdAndNameContainingAllIgnoringCase(courseId, name.trim(), pageable);
        }else{
            subjects = subjectRepository.findByCourseId(courseId, pageable);
        }
        return subjects;
    }

    @Override
    @Transactional
    public Subject addSubject(Long courseId, Subject subject) {
        Assert.notNull(courseId, "Course must not be null");
        subject.setCourse(get(courseId));
        return subjectRepository.save(subject);
    }

}
