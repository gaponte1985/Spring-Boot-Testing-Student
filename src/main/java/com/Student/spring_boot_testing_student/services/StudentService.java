package com.Student.spring_boot_testing_student.services;

import com.Student.spring_boot_testing_student.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Student saveStudent(Student student);
    List<Student> getAllStudents();
    Optional<Student> getStudentById(Long id);
    Student updateStudent(Student student);
    void deleteStudentById(Long id);
}
