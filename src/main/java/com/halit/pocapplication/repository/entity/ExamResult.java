package com.halit.pocapplication.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "tbl_exam_result")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExamResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @OneToOne
//    @JoinColumn(name = "student_id" ,referencedColumnName = "id")
    private Long studentId;
//    @OneToOne
//    @JoinColumn(name = "course_id" ,referencedColumnName = "id")
    private Long courseId;
    double score;

}
