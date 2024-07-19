package com.Student.spring_boot_testing_student;


import com.Student.spring_boot_testing_student.model.Student;
import com.Student.spring_boot_testing_student.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    //JUnit test for save student operation
    @Test
    @DisplayName("")
    public void givenStudentObject_whenSave_thenReturnSavedStudent() {
        //Given - precondition or setup
        Student student = Student.builder().firstName("Gerardo").lastName("Aponte").email("aponte@test.com").build();

        //When - action or the behaviour that we are going to test
        Student savedStudent = studentRepository.save(student);

        //Then - verify the output
        Assertions.assertNotNull(savedStudent);
        Assertions.assertNotNull(savedStudent.getId());

    }
}
