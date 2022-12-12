package com.halit.pocapplication.controller;

import com.halit.pocapplication.constants.Constants;
import com.halit.pocapplication.repository.entity.Course;
import com.halit.pocapplication.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.halit.pocapplication.constants.ApiUrls.*;


@RequestMapping(VERSION + WEB + COURSE)
@RequiredArgsConstructor
@Controller
public class CourseController {
    private final CourseService courseService;
//    @PostMapping(SAVE)
    public ResponseEntity<Course> save(String name) {
        try {
            Optional<Course> course0 = courseService.findByName(name);
            if(course0.isEmpty()){
            Course course1=Course.builder()
                    .name(name)
                    .build();
            courseService.save(course1);
            System.out.println("Course kaydı başarılı");
            return ResponseEntity.ok(course1);}
            else{
                return ResponseEntity.ok(course0.get());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Course kaydı basarisiz");
            throw new RuntimeException();

        }
    }

    @GetMapping(GETALL)
    public ResponseEntity<List<Course>> findAll() {
        return ResponseEntity.ok(courseService.findAll());
    }

    List<Course> coursesList = new ArrayList<>();


    @GetMapping("/form")
    public String getForm(Model model, @RequestParam(required = false) Long id) {
        int index = getGradeIndex(id);
        model.addAttribute("course", index == Constants.NOT_FOUND ? new Course() : coursesList.get(index));
        return "form";
    }

    @PostMapping("/handleSubmit")
    public String submitForm(Course course) {
        int index = getGradeIndex(course.getId());
        if (index == Constants.NOT_FOUND) {
            save(course.getName());

        } else {
            return "course already created.";
        }
        return "redirect:/mkv/web/course/courses";
    }

    @GetMapping("/courses")
    public String getGrades(Model model) {
        List<Course> courses = findAll().getBody();
        model.addAttribute("courses", courses);
        return "course";
    }

    public int getGradeIndex(Long id) {
        for (int i = 0; i < coursesList.size(); i++) {
            if (coursesList.get(i).getId().equals(id)) return i;
        }
        return Constants.NOT_FOUND;
    }
}
