package com.Student.spring_boot_testing_student.services.impl;


import com.Student.spring_boot_testing_student.exception.ResourceNotFoundException;
import com.Student.spring_boot_testing_student.model.Student;
import com.Student.spring_boot_testing_student.repository.StudentRepository;
import com.Student.spring_boot_testing_student.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StudentServiceImpl implements StudentService {


    private StudentRepository studentRepository;

    @Override
    public Student saveStudent(Student student) {
        Optional<Student> saveStudent = studentRepository.findByEmail(student.getEmail());
        if (saveStudent.isPresent()) {
            throw new ResourceNotFoundException("Student with email " + student.getEmail() + " already exists");

        }
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {

        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> getStudentById(Long id) {

        return studentRepository.findById(id);
    }

    @Override
    public Student updateStudent(Student updateStudent) {
        return studentRepository.save(updateStudent);
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    @Autowired
    public void StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
}
