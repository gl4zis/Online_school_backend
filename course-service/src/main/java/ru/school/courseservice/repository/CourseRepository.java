package ru.school.courseservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.school.courseservice.model.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
}