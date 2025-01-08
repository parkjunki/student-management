package org.fastcampus.student_management.application.course.interfaces;

import java.util.List;
import org.fastcampus.student_management.domain.Course;

public interface CourseCommandRepository {
    void save(Course course);

    void saveCourses(List<Course> courses);
}
