package com.broadfaster.jpa.repository;

import com.broadfaster.jpa.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

//    Custom Pagination with Sorting method

    Page<Course> findByTitleContaining(String title, Pageable pageable);
}
