package com.juanje.cursosboot.web;

import com.juanje.cursosboot.domain.Teacher;
import com.juanje.cursosboot.service.TeacherService;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class TeacherControllerTest {

    private TeacherService teacherService = mock(TeacherService.class);
    private TeacherController sut = new TeacherController(teacherService);

    @Test
    public void findTeachersShouldCallServiceFindTeachersAndReturnResult(){
        Page expected = mock(Page.class);
        Pageable pageable = mock(Pageable.class);
        doReturn(expected).when(teacherService).findTeachers("testName", pageable);

        Page<Teacher> result = sut.findTeachers("testName", pageable);

        verify(teacherService).findTeachers("testName", pageable);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void getShouldCallServiceGetAndReturnResult(){
        Teacher expected = mock(Teacher.class);
        doReturn(expected).when(teacherService).get(1L);

        Teacher result = sut.get(1L);

        verify(teacherService).get(1L);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void addShouldCallServiceAddAndReturnResult(){
        Teacher teacher = mock(Teacher.class);
        Teacher expected = mock(Teacher.class);
        doReturn(expected).when(teacherService).add(teacher);

        Teacher result = sut.add(teacher);

        verify(teacherService).add(teacher);
        assertThat(result, equalTo(expected));
    }
}
