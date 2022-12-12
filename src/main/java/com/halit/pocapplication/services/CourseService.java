package com.halit.pocapplication.services;

import com.halit.pocapplication.repository.CourseRepository;
import com.halit.pocapplication.repository.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public Course save(Course course){
        return courseRepository.save(course);
    }

    public List<Course> findAll() {
        return  courseRepository.findAll();
    }

    public Optional<Course> findById(Long id) {
        return  courseRepository.findOptionalById(id);
    }

    public Optional<Course> findByName(String name) {
        return courseRepository.findOptionalByName(name);
    }

//    public Boolean updateCompany(Course company) {
//        Optional<Course> company1 = findById(company.getId());
//        if(company1.isPresent()&&company1.get().getStatus().equals(Status.ACTIVE)){
//            company1.get().setCity(company.getCity());
//            company1.get().setName(company.getName());
//            company1.get().setSector(company.getSector());
//            courseRepository.save(company1.get());
//            return true;
//        }else{
//            return false;
//        }
//    }



//    public Boolean deleteCompany(Course company) {
//        Optional<Course> company1 = findById(company.getId());
//        System.out.println();
//        if(company1.isPresent() && company1.get().getStatus().equals(Status.ACTIVE)){
//            company1.get().setStatus(Status.DELETED);
//            courseRepository.save(company1.get());
//            return true;
//        }else{
//            return false;
//        }
//    }
}
