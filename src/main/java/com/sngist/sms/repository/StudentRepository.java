package com.sngist.sms.repository;

import com.sngist.sms.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

   public abstract List<Student> getStudentById(Long id);
    @Query("SELECT s from Student  s WHERE s.name=?1 and s.age=?2")
    List<Student> getStudentByNaAndAge(String name,Integer age );

}
