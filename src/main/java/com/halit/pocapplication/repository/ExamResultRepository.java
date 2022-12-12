package com.halit.pocapplication.repository;

import com.halit.pocapplication.dto.response.ExamResultAverageDto;
import com.halit.pocapplication.repository.entity.ExamResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExamResultRepository extends JpaRepository<ExamResult,Long> {
    Optional<ExamResult> findOptionalById(Long id);

    @Query(value = "select count(*) from tbl_exam_result as u where u.student_id=?1 and u.course_id=?2",nativeQuery = true)
    int findByCourseIdAndStudentId(Long studentId,Long courseId);
    @Query(value = "select AVG(score) from tbl_exam_result where student_id=?1 and course_id=?2 ",nativeQuery = true)
    double avgByCourseIdAndStudentId(Long studentId, Long courseId);

//    Optional<List<ExamResultAverageDto>> findAllByStudentId(Long id);
//    @Query("select u from User  u where length( u.password)> ?1")
//    Optional<List<ExamResult>> controlPasswordlength(int value);
//
//    @Query("select u from User  u where length( u.password)> :value")
//    Optional<List<ExamResult>> controlPasswordlength2(@Param("value") int value);
}
