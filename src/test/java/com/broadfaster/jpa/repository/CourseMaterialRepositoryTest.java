package com.broadfaster.jpa.repository;

import com.broadfaster.jpa.entity.Course;
import com.broadfaster.jpa.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;


@SpringBootTest
@ExtendWith(SpringExtension.class)
class CourseMaterialRepositoryTest {


    @Autowired
    private CourseMaterialRepository repository;

//    Below test case we need to have Cascading because we are saving  courseMaterial for the course which doesnot exist in course table so
//    through cascading we can achieve it, while we are putting the courseMaterial into db it also save the course which we are passing through.
    @Test
    public void saveCourseMaterial() {

        Course course = Course.builder()
                .title("DSA")
                .credit(90)
                .build();

        CourseMaterial courseMaterial = CourseMaterial.builder()
                .url("www.google.com")
                .course(course)
                .build();

        repository.save(courseMaterial);
    }

    @Test
    public void printAllCourseMaterial(){
//        FetchType.LAZY =  we are getting only the courseMaterial over here because we have set the fetch type as Lazy
        List<CourseMaterial> courseMaterials = repository.findAll();
        System.out.println("courseMaterials = " + courseMaterials);
    }



}