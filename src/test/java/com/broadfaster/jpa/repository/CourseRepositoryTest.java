package com.broadfaster.jpa.repository;

import com.broadfaster.jpa.entity.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printCourses() {
        List<Course> courses = courseRepository.findAll();
//        Here we are getting only course items as well but if want to course material as well for ALL COURSES we need to implement bi-directional one to one mapping
        System.out.println("courses = " + courses);

    }

}