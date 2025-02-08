package com.broadfaster.jpa.repository;

import com.broadfaster.jpa.entity.Guardian;
import com.broadfaster.jpa.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

//@DataJpaTest : we should use this annotation to test our repository layer because it does not commit data into database and after testing it will flush the data but we are using @SpringBootTest because we want to see data into database
@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent(){
//        we are going use the builder patter to create the object of entity class
        Guardian guardian = Guardian.builder()
                .name("utkarsh")
                .email("utkarsh@gmail.com")
                .mobile("9384950395")
                .build();

        Student student = Student.builder()
                .firstName("akshat")
                .lastName("kumar")
                .emailId("akshat20kumar@gmail.com")
//                .guardianName("utkarsh")
//                .guardingEmail("ut@gmail.com")
//                .guardingMobile("9834723474")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian() {
        Guardian guardian = Guardian.builder()
                .name("utkarsh")
                .email("utkarshrj@gmail.com")
                .mobile("9384950395")
                .build();

        Student student = Student.builder()
                .firstName("raj")
                .lastName("kumar")
                .emailId("rajkumar@gmail.com")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }

    @Test
    public void printAllStudent()  {
        List<Student> studentList = studentRepository.findAll();
        System.out.println("studentList = " + studentList);
    }

    @Test
    public void printStudentById() {
        Long idToFind = 2L;
        Student student = studentRepository.findById(idToFind).get();
        System.out.println("student = " + student);
    }

    @Test
    public void printStudentByFirstName() {
//        for this we need to create a custom method in repository interface as jpa need to create custom query based on the entity class

        List<Student> studentList = studentRepository.findByFirstName("raj");
        System.out.println("studentList = " + studentList);
    }


    @Test
    public void  printStudentByFirstNameContaining(){
        List<Student>  studentList = studentRepository.findByFirstNameContaining("ak");
        System.out.println("studentList = " + studentList);
    }

    @Test
    public void printStudentByLastNameNotNull(){
        List<Student> studentList= studentRepository.findByLastNameNotNull();
        System.out.println("studentList = " + studentList);
    }

    @Test
//    we want  this find by email address because emailId is applied  with unique constraint
    public void printStudentByEmailAddress() {
        Student student = studentRepository.getStudentByEmailAddress("akshat20kumar@gmail.com");
        System.out.println("student = " + student);
    }

    @Test
    public void printStudentFirstNameByEmailAddress(){
        String studentFirstName = studentRepository.getStudentFirstNameByEmailAddress("akshat20kumar@gmail.com");
        System.out.println("studentFirstName = " + studentFirstName);
    }

    @Test
    public void printGetStudentByEmailAddressNative(){
        Student student =
                studentRepository.getStudentByEmailAddressNative(
                        "akshat20kumar@gmail.com"
                );

        System.out.println("student = " + student);
    }

    @Test
    public void printGetStudentByEmailAddressNativeNamedParam() {
        Student student =
                studentRepository.getStudentByEmailAddressNativeNamedParam(
                        "akshat20kumar@gmail.com"
                );

        System.out.println("student = " + student);
    }

    @Test
    public void updateStudentNameByEmailIdTest() {
        studentRepository.updateStudentNameByEmailId(
                "Jod",
                "akshat20kumar@gmail.com");
    }

}