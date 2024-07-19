package com.Student.spring_boot_testing_student.controller;


import com.Student.spring_boot_testing_student.model.Student;
import com.Student.spring_boot_testing_student.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Long studentID) {
        return studentService.getStudentById(studentID)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") Long studentID, @RequestBody Student student) {
        return studentService.getStudentById(studentID)
                .map(saveStudent -> {
                    saveStudent.setFirstName(student.getFirstName());
                    saveStudent.setLastName(student.getLastName());
                    saveStudent.setEmail(student.getEmail());
                    Student updatedEmployee = studentService.updateStudent(saveStudent);
                    return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);

                })
                .orElseGet(() -> ResponseEntity.notFound().build());}

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") Long studentID) {
        studentService.deleteStudentById(studentID);
        return new ResponseEntity<>("Student deleted", HttpStatus.OK);
    }
}
