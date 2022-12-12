package com.halit.pocapplication.services;

import com.halit.pocapplication.repository.CourseRepository;
import com.halit.pocapplication.repository.ExamResultRepository;
import com.halit.pocapplication.repository.entity.Course;
import com.halit.pocapplication.repository.entity.ExamResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamResultService {
    @Autowired
    private ExamResultRepository examResultRepository;

    public ExamResult save(ExamResult examResult){
        return examResultRepository.save(examResult);
    }

    public List<ExamResult> findAll() {
        return  examResultRepository.findAll();
    }
    public Optional<ExamResult> findById(Long id) {
        return  examResultRepository.findOptionalById(id);
    }
    public Boolean findByCourseIdAndStudentId(Long studentId, Long courseId){
        if(examResultRepository.findByCourseIdAndStudentId(studentId,courseId)<3) {
            return true;
        }else{
            return false;
        }
    }
}
