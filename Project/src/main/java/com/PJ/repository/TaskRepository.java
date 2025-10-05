package com.PJ.repository;

import com.PJ.model.Task;
import com.PJ.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStudentId(Long studentId);
    // ✅ เพิ่มเมธอดใหม่
    List<Task> findByStudent(User student);

    // ✅ เพิ่มเมธอดใหม่ สำหรับกรณีกรองตาม subject
    List<Task> findByStudentAndSubjectId(User student, Long subjectId);
}