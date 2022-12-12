package com.halit.pocapplication.controller;

import com.halit.pocapplication.dto.request.ExamResultRequestDto;
import com.halit.pocapplication.repository.StudentRepository;
import com.halit.pocapplication.repository.entity.Course;
import com.halit.pocapplication.repository.entity.ExamResult;
import com.halit.pocapplication.repository.entity.Student;
import com.halit.pocapplication.services.CourseService;
import com.halit.pocapplication.services.ExamResultService;
import com.halit.pocapplication.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static com.halit.pocapplication.constants.ApiUrls.*;

@Controller
@RequestMapping(VERSION + WEB + EXAMRESULT)
@RequiredArgsConstructor
public class ExamResultController {
    private final ExamResultService examResultService;
    private final StudentService studentService;
    private final CourseService courseService;
    @PostMapping(SAVE)
    public ResponseEntity<ExamResult> save(@RequestBody ExamResult examResult) {
             try {

                     ExamResult examResult1 = ExamResult.builder()
                             .studentId(examResult.getStudentId())
                             .courseId(examResult.getCourseId())
                             .score(examResult.getScore())
                             .build();
                     examResultService.save(examResult1);
                     System.out.println("Result kaydı başarılı");
                     return ResponseEntity.ok(examResult1);

        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Result kaydı basarisiz");
            throw  new RuntimeException();
        }

        }

    @GetMapping(GETALL)
    public ResponseEntity<List<ExamResult>> findAll() {
        return ResponseEntity.ok(examResultService.findAll());
    }




    @GetMapping(FORM)
    public String getForm(Model model, @RequestParam(required = false) Long id) {
        model.addAttribute("examResult", new ExamResult());
        return "formER";
    }

    @PostMapping("/handleSubmit")
    public String submitForm(ExamResult examResult, RedirectAttributes attributes, Model model) {
        if(examResultService.findByCourseIdAndStudentId(examResult.getStudentId(),
                examResult.getCourseId())) {
        save(examResult);
        } else {
            attributes.addFlashAttribute("error", "Bu öğrenciye daha fazla not girilememektedir.");
            return "redirect:/mkv/web/examresult/examResults";
        }
        return "redirect:/mkv/web/examresult/examResults";

    }

    @GetMapping("/examResults")
    public String getStudents(Model model) {
        List<ExamResult> examResults = findAll().getBody();
        model.addAttribute("examResults", examResults);
        return "results";
    }
}
