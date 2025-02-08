package com.broadfaster.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {

    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    private Long courseId;
    private String title;
    private Integer credit;
//    for bi-directional one to one mapping
    @OneToOne(
//            in mapped by we need to pass the varible name on which Join Columns is done
            mappedBy = "course",
            optional = false
    )
    private CourseMaterial courseMaterial;


//    ManyToOne (JoinColumn annotation is used)

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "teacher_id",
            referencedColumnName = "teacherId"
    )
    private Teacher teacher;

//    ManyToMany (JoinTable annotation is used)
    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "student_course_mapping",
            joinColumns = @JoinColumn(
                    name = "course_id",
                    referencedColumnName = "courseId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "student_id",
                    referencedColumnName = "studentId"
            )
    )
    private List<Student> students;


//    utility method for adding Students obj to the List

    public void addStudent(Student  student){

//        local varibale "students check
        if(Objects.isNull(students)) {
            students = new ArrayList<>();
        }
        students.add(student);
    }
}
