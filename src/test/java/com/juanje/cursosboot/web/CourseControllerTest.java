package com.juanje.cursosboot.web;

import com.juanje.cursosboot.domain.Course;
import com.juanje.cursosboot.domain.Subject;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class CourseControllerTest {

    private CourseService courseService = mock(CourseService.class);
    private CourseController courseController = new CourseController(courseService);

    @Test
    public void findCoursesShouldCallServiceFindCoursesAndReturnResult(){
        Page<Course> page = mock(Page.class);
        Pageable pageable = mock(Pageable.class);
        doReturn(page).when(courseService).findCourses("testName", pageable);

        Page<Course> result = courseController.findCourses("testName", pageable);

        verify(courseService).findCourses("testName", pageable);
        assertThat(result, equalTo(page));
    }

    @Test
    public void getIdShouldCallServiceGetIdAndReturnResult(){
        Course course = mock(Course.class);
        doReturn(course).when(courseService).get(1L);

        Course result = courseController.get(1L);

        verify(courseService).get(1L);
        assertThat(result, equalTo(course));
    }

    @Test
    public void findSubjectsShouldCallServiceFindSubjectsAndReturnResult(){
        Page<Subject> page = mock(Page.class);
        Pageable pageable = mock(Pageable.class);
        doReturn(page).when(courseService).findSubjects(1L, "testName", pageable);

        Page<Subject> result = courseController.findSubjects(1L, "testName", pageable);

        verify(courseService).findSubjects(1L, "testName", pageable);
        assertThat(result, equalTo(page));
    }

    @Test
    public void addSubjectShouldCallServiceAddSubjectAndReturnResult(){
        Subject subject = mock(Subject.class);
        Subject subject1 = mock(Subject.class);
        doReturn(subject).when(courseService).addSubject(1L, subject1);

        Subject result = courseController.addSubject(1L, subject1);

        verify(courseService).addSubject(1L, subject1);
        assertThat(result, equalTo(subject));
    }
}
