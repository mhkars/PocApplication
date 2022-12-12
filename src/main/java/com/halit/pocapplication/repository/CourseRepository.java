package com.halit.pocapplication.repository;

import com.halit.pocapplication.repository.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    Optional<Course> findOptionalById(Long companyId);

    Optional<Course> findOptionalByName(String name);
}
