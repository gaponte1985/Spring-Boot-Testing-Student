package com.Student.spring_boot_testing_student.repository;

import com.Student.spring_boot_testing_student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByEmail(String email);
    @Query("select e from Student e where e.firstName = ?1 and e.lastName = ?2 ")
    Student findByJPQL(String firstName, String lastName);

    @Query("select e from Student e where e.firstName = :firstName and e.lastName = :lastName ")
    Student findByJPQLNameParams(@Param("firstName") String firstName, @Param("lastName") String lastName);

    @Query(value = "select * from student e where e.first_name = ?1 and e.last_name = ?2", nativeQuery = true)
    Student findByNativeSQL(String firstName, String lastName);

    @Query(value = "select * from student e where e.first_name = :firstName and e.last_name = :lastName", nativeQuery = true)
    Student findByNativeSQLNameParams(@Param("firstName") String firstName, @Param("lastName") String lastName);

}
