package com.broadfaster.jpa.repository;

import com.broadfaster.jpa.entity.Course;
import com.broadfaster.jpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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

    @Test
    public void saveCourseWithTeacher() {
        Teacher teacher = Teacher.builder()
                .firstName("Shweta")
                .lastName("Singh")
                .build();

        Course  course = Course.builder()
                .title("Java")
                .credit(45)
                .teacher(teacher)
                .build();

        courseRepository.save(course);
    }


// Pagination

    @Test
    public void findAllPagination() {

       Pageable  firstPageWithThreeRequest =  PageRequest.of(0, 3);

       Pageable secondPageWithTwoRecords = PageRequest.of(1, 2);

       List<Course>  courses = courseRepository.findAll(firstPageWithThreeRequest).getContent();

       Long totalElements = courseRepository.findAll(firstPageWithThreeRequest).getTotalElements();
       Long totalPages =(long) courseRepository.findAll(firstPageWithThreeRequest).getTotalPages();
        System.out.println("courses = " + courses);
        System.out.println("totalElements = " + totalElements);
        System.out.println("totalPages = " + totalPages);
    }

//    Pagination with Sorting

    @Test
    public void findAllSorting() {
        Pageable sortByTitle = PageRequest.of(0, 2, Sort.by("title"));
        Pageable sortByCreditDesc = PageRequest.of(0, 2, Sort.by("credit").descending());
        Pageable sortByCreditAndTitle = PageRequest.of(0, 2, Sort.by("credit").descending().and(Sort.by("title")));

        List<Course> courses = courseRepository.findAll(sortByTitle).getContent();

        System.out.println("courses = " + courses);
    }

//    Custom Pagination With Sorting

    @Test
    public void printFindByTitleContaining(){
        Pageable firstPageTenRecords = PageRequest.of(0, 10);

//        we will get only courses records which have D in their title
        List<Course> courses = courseRepository.findByTitleContaining("D", firstPageTenRecords).getContent();
        System.out.println("courses = " + courses);
    }
}