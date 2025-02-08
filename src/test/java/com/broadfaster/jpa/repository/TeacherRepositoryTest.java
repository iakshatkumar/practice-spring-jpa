package com.broadfaster.jpa.repository;

import com.broadfaster.jpa.entity.Course;
import com.broadfaster.jpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;


//    NOTE: we have changed the implementation from OneToMany mapping to ManyToOne mapping
//    @Test
//    public void saveTeacher() {
//
//        Course course = Course.builder()
//                .title("DSA")
//                .credit(54)
//                .build();
//
//        Course course1 = Course.builder()
//                .title("SpringBoot")
//                .credit(102)
//                .build();
//
//        Teacher teacher =  Teacher.builder()
//                .firstName("Ajit")
//                .lastName("Tiwari")
//                .courses(List.of(course, course1))
//                .build();
//
//        teacherRepository.save(teacher);
//    }
}