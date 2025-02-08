package com.broadfaster.jpa.repository;

import com.broadfaster.jpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

//    here method name matters like it must match the field name present in the entity class but method parameter name can be anything
    public List<Student> findByFirstName(String name);

    public List<Student> findByFirstNameContaining(String name);

    public List<Student> findByLastNameNotNull();

//    here  Student entity has guardian attribute and inside guarding we have name attribute hence we have created name of below as is.
//    public List<Student> findByGuardianName();

//    finding based on 2 attributes
    public Student findByFirstNameAndLastName(String firstName, String LastName);

//    JPQL query are defined based  on the entity class we have defined not the table which is created in the database
//    ?1 means 1st parameter which passed to the method
    @Query(value = "select s from Student s where s.emailId = ?1")
    Student getStudentByEmailAddress(String emailId);

    @Query(value = "select s.firstName from Student s where s.emailId = ?1")
    String getStudentFirstNameByEmailAddress(String emailId);


//    Native Query  =  are defined based  on the dbms tables and attributes you have
    @Query(
            value = "SELECT * FROM tbl_student s where s.email_address = ?1",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNative(String email);


//  Native Named Param
    @Query(
            value = "SELECT * FROM tbl_student s where s.email_address = :emailId",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNativeNamedParam(@Param("emailId") String emailId);



//    here we are updating the data in  database so marked  as @Modifying and also @Transactional to add on the ACID propties to the modify operations (ideally Transactional should be in service class either method or class)
    @Query(
             value = "Update tbl_student set first_name = ?1 where email_address = ?2",
            nativeQuery = true
    )
    @Modifying
    @Transactional
    Integer updateStudentNameByEmailId(String firstName, String emailId);




}
