package com.halit.pocapplication.dto.request;

import com.halit.pocapplication.repository.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamResultRequestDto {

    private Long studentId;
    private Long courseId;
    private double score;

}
