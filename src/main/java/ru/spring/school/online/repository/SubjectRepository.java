package ru.spring.school.online.repository;

import org.springframework.data.repository.CrudRepository;
import ru.spring.school.online.model.security.Subject;

public interface SubjectRepository extends CrudRepository<Subject, Long> {
}