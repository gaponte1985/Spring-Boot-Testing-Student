package com.Student.spring_boot_testing_student;

import com.Student.spring_boot_testing_student.integration.AbstractionBaseTest;
import com.Student.spring_boot_testing_student.model.Student;
import com.Student.spring_boot_testing_student.repository.StudentRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class SpringBootTestingStudentApplicationTests extends AbstractionBaseTest {

	@Autowired
    private StudentRepository studentRepository;

	@Autowired
	private MockMvc mockMvc;

	//JUnit test for
	@Test
	@DisplayName("Junit ")
	public void givenStudents_whenGetAllStudents_thenListOfStudents() throws Exception {
		//Given - precondition or setup
		List<Student> students=
				List.of(Student.builder().firstName("Gerardo").lastName("Aponte").email("aponte@test.com").build(),
				Student.builder().firstName("Ariel").lastName("Garcia").email("ariael@test.com").build());
		studentRepository.saveAll(students);
		//When - action or the behaviour that we are going to test
		ResultActions response= mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/students"));
		//Then - verify the output
		response.andExpect(MockMvcResultMatchers.status().isOk());
		response.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(students.size())));;
	}
}
