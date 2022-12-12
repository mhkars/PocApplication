package com.halit.pocapplication.controller;

import com.halit.pocapplication.constants.Constants;
import com.halit.pocapplication.dto.response.ExamResultAverageDto;
import com.halit.pocapplication.repository.CourseRepository;
import com.halit.pocapplication.repository.ExamResultRepository;
import com.halit.pocapplication.repository.StudentRepository;
import com.halit.pocapplication.repository.entity.Course;
import com.halit.pocapplication.repository.entity.ExamResult;
import com.halit.pocapplication.repository.entity.Student;
import com.halit.pocapplication.services.CourseService;
import com.halit.pocapplication.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.halit.pocapplication.constants.ApiUrls.*;

@Controller
@RequestMapping(VERSION + WEB + STUDENT)
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    public ResponseEntity<Student> save(Student student) {
        try {
            Optional<Student> student0 = studentService.findByNumber(student.getNumber());
            if(student0.isEmpty()){
                Student student1=Student.builder()
                        .full_name(student.getFull_name())
                        .number(student.getNumber())
                        .email(student.getEmail())
                        .gsm_number(student.getGsm_number())
                        .build();
                studentService.save(student1);
                System.out.println("Student kaydı başarılı");
                return ResponseEntity.ok(student1);}
            else{
                return ResponseEntity.ok(student0.get());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Student kaydı basarisiz");
            throw new RuntimeException();

        }
    }



    @GetMapping(GETALL)
    public ResponseEntity<List<Student>> findAll() {
        return ResponseEntity.ok(studentService.findAll());
    }

    private final StudentRepository studentRepository;
    private final ExamResultRepository examResultRepository;
    private final CourseRepository courseRepository;


    @GetMapping(FORM)
    public String getForm(Model model, @RequestParam(required = false) Long id) {
        model.addAttribute("student", new Student());
        return "formStu";
    }

    @PostMapping("/handleSubmit")
    public String submitForm(Student student) {
        save(student);

        return "redirect:/mkv/web/student/students";
    }

    @GetMapping("/averages")
    public String getAverages(Model model,Long id) {
        Optional<Student> student = studentService.findById(id);
        List<Course> courses = courseRepository.findAll();
        List<ExamResult> newlist = new ArrayList<>();
        for(int i = 0; i<courses.size();i++){
        ExamResult result =ExamResult.builder()
                .courseId(courses.get(i).getId())
                .score(examResultRepository.avgByCourseIdAndStudentId(id,courses.get(i).getId()))
                .build();
            newlist.add(result);
            System.out.println(result);
            System.out.println(newlist);
        }

        model.addAttribute("averages", newlist);

        return "averages";
    }


    @GetMapping("/students")
    public String getStudents(Model model) {
        List<Student> students = findAll().getBody();
        model.addAttribute("students", students);
        return "student";
    }

}


