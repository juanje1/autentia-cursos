package com.juanje.cursosboot.service;

import com.juanje.cursosboot.data.CourseRepository;
import com.juanje.cursosboot.data.SubjectRepository;
import com.juanje.cursosboot.domain.Course;
import com.juanje.cursosboot.domain.Subject;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class CourseServiceTest {

    private CourseRepository courseRepository = mock(CourseRepository.class);
    private SubjectRepository subjectRepository = mock(SubjectRepository.class);
    private CourseService courseService = new CourseServiceImpl(courseRepository, subjectRepository);

    @Test
    public void findCoursesShouldCallServiceFindCoursesAndReturnResult(){
        Page<Course> page = mock(Page.class);
        Pageable pageable = mock(Pageable.class);
        doReturn(page).when(courseRepository).findAll(pageable);

        Page<Course> result = courseService.findCourses(null, pageable);

        verify(courseRepository).findAll(pageable);
        assertThat(result, equalTo(page));
    }

    @Test
    public void findCoursesShouldCallServiceFindCoursesContainingAllIgnoringCaseAndReturnResult(){
        Page<Course> page = mock(Page.class);
        Pageable pageable = mock(Pageable.class);
        doReturn(page).when(courseRepository).findByNameContainingAllIgnoringCase("testName", pageable);

        Page<Course> result = courseService.findCourses("testName", pageable);

        verify(courseRepository).findByNameContainingAllIgnoringCase("testName", pageable);
        assertThat(result, equalTo(page));
    }

    @Test
    public void getShouldCallServiceGetAndReturnResult(){
        Course course = mock(Course.class);
        doReturn(course).when(courseRepository).findById(1L);

        Course result = courseService.get(1L);

        verify(courseRepository).findById(1L);
        assertThat(result, equalTo(course));
    }

    @Test
    public void findSubjectsShouldCallFindSubjectsAndReturnResult(){
        Page<Subject> page = mock(Page.class);
        Pageable pageable = mock(Pageable.class);
        doReturn(page).when(subjectRepository).findByCourseId(1L, pageable);

        Page<Subject> result = courseService.findSubjects(1L, null, pageable);

        verify(subjectRepository).findByCourseId(1L, pageable);
        assertThat(result, equalTo(page));
    }

    @Test
    public void findSubjectsShouldCallFindSubjectsContainingAllIgnoringCaseAndReturnResult(){
        Page<Subject> page = mock(Page.class);
        Pageable pageable = mock(Pageable.class);
        doReturn(page).when(subjectRepository).findByCourseIdAndNameContainingAllIgnoringCase(1L, "testName", pageable);

        Page<Subject> result = courseService.findSubjects(1L, "testName", pageable);

        verify(subjectRepository).findByCourseIdAndNameContainingAllIgnoringCase(1L, "testName", pageable);
        assertThat(result, equalTo(page));
    }

    @Test
    public void addSubjectShouldCallAddSubjectAndReturnResult(){
        Subject subject = mock(Subject.class);
        Subject subject1 = mock(Subject.class);
        doReturn(subject).when(subjectRepository).save(subject1);

        Subject result = courseService.addSubject(1L, subject1);

        verify(subjectRepository).save(subject1);
        assertThat(result, equalTo(subject));
    }
}
