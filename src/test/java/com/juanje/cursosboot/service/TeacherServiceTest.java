package com.juanje.cursosboot.service;

import com.juanje.cursosboot.data.CourseRepository;
import com.juanje.cursosboot.data.TeacherRepository;
import com.juanje.cursosboot.domain.Teacher;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class TeacherServiceTest {

    private TeacherRepository teacherRepository = mock(TeacherRepository.class);
    private CourseRepository courseRepository = mock(CourseRepository.class);

    private TeacherService sut = new TeacherServiceImpl(teacherRepository, courseRepository);

    @Test
    public void findTeachersWithoutNameShouldCallFindAllAndReturnTheirs() {
        Pageable pageable = mock(Pageable.class);
        Page expected = mock(Page.class);
        doReturn(expected).when(teacherRepository).findAll(pageable);

        Page<Teacher> result = sut.findTeachers(null, pageable);

        verify(teacherRepository).findAll(pageable);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void findTeachersWithNameShouldCallFindByNameContainingAllIgnoringCaseAndReturnTheirs() {
        Pageable pageable = mock(Pageable.class);
        Page expected = mock(Page.class);
        doReturn(expected).when(teacherRepository).findByNameContainingAllIgnoringCase("test", pageable);

        Page<Teacher> result = sut.findTeachers("test", pageable);

        verify(teacherRepository).findByNameContainingAllIgnoringCase("test", pageable);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void getTeacherByIdShouldCallFindById() {
        Teacher expected = mock(Teacher.class);
        doReturn(expected).when(teacherRepository).findById(1L);

        Teacher result = sut.get(1L);

        verify(teacherRepository).findById(1L);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void addShouldCallToSave() {
        Teacher expected = mock(Teacher.class);
        Teacher teacher = mock(Teacher.class);
        doReturn(expected).when(teacherRepository).save(teacher);

        Teacher result = sut.add(teacher);

        verify(teacherRepository).save(teacher);
        assertThat(result, equalTo(expected));
    }
}
