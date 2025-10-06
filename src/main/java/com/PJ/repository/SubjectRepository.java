package com.PJ.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PJ.model.Subject;
import com.PJ.model.User;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findByUser(User user);
    Optional<Subject> findByNameAndUser(String name, User user);
    List<Subject> findByUserAndName(User user, String name);
}