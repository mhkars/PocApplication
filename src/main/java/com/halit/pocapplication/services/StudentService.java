package com.halit.pocapplication.services;

import com.halit.pocapplication.repository.CourseRepository;
import com.halit.pocapplication.repository.StudentRepository;
import com.halit.pocapplication.repository.entity.Course;
import com.halit.pocapplication.repository.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student save(Student student){
        return studentRepository.save(student);
    }

    public List<Student> findAll() {
        return  studentRepository.findAll();
    }

    public Optional<Student> findById(Long id) {
        return  studentRepository.findOptionalById(id);
    }

    public Optional<Student> findByNumber(String number) {
        return studentRepository.findOptionalByNumber(number);
    }
}
