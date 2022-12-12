package com.halit.pocapplication.repository;

import com.halit.pocapplication.repository.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    Optional<Student> findOptionalById(Long id);

    Optional<Student> findOptionalByNumber(String number);
}
