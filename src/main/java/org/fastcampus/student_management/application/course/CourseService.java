package org.fastcampus.student_management.application.course;
import java.util.List;
import org.fastcampus.student_management.application.course.dto.CourseInfoDto;
import org.fastcampus.student_management.application.student.StudentService;
import org.fastcampus.student_management.domain.Course;
import org.fastcampus.student_management.domain.DayOfWeek;
import org.fastcampus.student_management.domain.Student;
import org.fastcampus.student_management.repo.CourseRepository;

public class CourseService {
  private final CourseRepository courseRepository;
  private final StudentService studentService;

  public CourseService(CourseRepository courseRepository, StudentService studentService) {
    this.courseRepository = courseRepository;
    this.studentService = studentService;
  }

  public void registerCourse(CourseInfoDto courseInfoDto) {
    Student student = studentService.getStudent(courseInfoDto.getStudentName());
    Course course = new Course(student, courseInfoDto.getCourseName(), courseInfoDto.getFee(), courseInfoDto.getDayOfWeek(), courseInfoDto.getCourseTime());
    courseRepository.save(course);
  }

  public List<CourseInfoDto> getCourseDayOfWeek(DayOfWeek dayOfWeek) {
    // TODO: 과제 구현 부분
    List<Course> courseList = courseRepository.getCourseDayOfWeek(dayOfWeek);

    return courseList.stream().map(CourseInfoDto::new).toList();
  }

  //특정 학생의 수강료를 변경 시키면 특정 학생 수업에 전체에 적용이 되어야 함
  public void changeFee(String studentName, int fee) {
    // TODO: 과제 구현 부분
    List<Course> courseList = courseRepository.getCourseListByStudent(studentName);

    for(Course course : courseList) {
      course.setFee(fee);
    }
  }
}
